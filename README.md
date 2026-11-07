# TripSync — Phase 1 cross-platform foundation

Collaborative, time-limited travel albums. Monorepo includes React web, Expo Android/iOS starter, Spring Boot API, PostgreSQL, S3-compatible object storage, invitation-token workflow, Docker Compose and Terraform starter.

## Architecture
- `frontend/`: React + TypeScript + Vite web client.
- `mobile/`: Expo React Native Android/iOS client. Camera-roll picker and upload path are implemented; native background monitoring is explicitly an adapter milestone (see `mobile/README.md`).
- `backend/`: Java 21 / Spring Boot REST API, JPA, PostgreSQL and AWS SDK S3 presigned PUT/GET URLs.
- `infra/`: Terraform starter. Not a complete production environment.

## Local development
Prerequisites: Docker Compose, Java 21, Maven 3.9+, Node 20+, npm. For mobile, install Expo Go or native Android/iOS toolchains.

1. Start services: `docker compose up -d` from repository root.
2. Create local bucket `tripsync-media` in MinIO console `http://localhost:9001` (minioadmin/minioadmin). Configure bucket CORS to allow `http://localhost:5173` and PUT/GET/HEAD.
3. API: `cd backend && mvn spring-boot:run` (8080).
4. Web: `cd frontend && npm install && npm run dev` (5173).
5. Mobile: `cd mobile && cp .env.example .env && npm install && npx expo start`. For a physical phone set API URL to your development computer LAN IP.

## Phase 1 API
- `POST /api/groups`, `GET /api/groups`, `GET /api/groups/{id}`
- `POST /api/groups/{id}/invitations` — owner creates a 7-day invitation token. Delivery to email/SMS is not wired yet.
- `POST /api/invitations/{token}/accept` — accept invitation for authenticated user identity.
- `GET /api/groups/{id}/members` — list members after membership authorization.
- `POST /api/groups/{id}/media/upload-url` — create pending media record and presigned PUT.
- `POST /api/media/{id}/complete` — mark upload complete (production must verify object exists, size and checksum before READY).
- `GET /api/groups/{id}/media`, `GET /api/media/{id}/download-url`
- `POST /api/media/{id}/share-link`, `GET /api/share/{token}`

## Security warning — do not expose this starter publicly
`X-User-Id` is a local-development identity shim. It is not authentication. Replace it with AWS Cognito JWT validation and derive user identity only from the verified JWT `sub`; never trust a client-provided user ID. Add authorization tests for every endpoint. Invitation acceptance must bind invitee identity to verified email/phone, use hashed single-use tokens, rate limit and audit. The invitation endpoint currently returns the token for local development and has no outbound email/SMS provider.

## Upload design
Browser/mobile requests an upload ticket; API validates group membership and issues a short-lived S3 PUT URL; client uploads bytes directly to private object storage; client confirms completion. For production, use multipart S3 uploads for large videos, server-side `HeadObject` validation, checksum verification, idempotency keys, quarantine bucket/prefix, malware scanning and SQS-driven media processing. Do not proxy media bytes through Spring Boot.

## Auto-upload design
Manual uploads are supported by web and mobile. Mobile includes an opt-in preference and media permission request. This is not yet a complete background uploader. Implement a durable queue, media-library change detection, duplicate hashing, Wi-Fi-only controls, retry/backoff, pause/resume and visible consent. Android should use MediaStore + WorkManager; iOS PhotoKit + background URLSession. OS background execution is opportunistic; do not promise instant upload. Never upload an entire library without explicit group-scoped consent.

## Production architecture (target)
CloudFront (web) -> ALB -> ECS Fargate (Spring API); Cognito for identity; RDS PostgreSQL; private S3 bucket with SSE-KMS; S3 events -> SQS -> isolated worker for EXIF extraction, thumbnails and FFmpeg preview renditions; CloudWatch logs/alarms; Secrets Manager; Terraform-managed IAM/VPC. Use private subnets for API/database, least-privilege task roles, bucket public-access block, lifecycle policies and backups.

## Production readiness checklist
- Cognito OIDC resource-server validation, JWT subject mapping, refresh/logout and account recovery.
- Invite delivery via SES/SNS, verified contact matching, hashed one-time tokens and revocation.
- Group roles (OWNER/ADMIN/MEMBER), membership listing/removal and ownership transfer.
- S3 multipart upload, object existence/content validation, checksums, malware scanning and quotas.
- EXIF parsing and privacy controls; strip GPS from public/shared renditions by default unless explicitly allowed.
- Cursor pagination, search, rate limiting, audit events, idempotency and optimistic locking.
- Native upload queue and device tests on physical Android/iOS devices.
- Integration/E2E tests, CI, dependency scanning, backups/restore drills, metrics and tracing.
- Data retention/expiry state machine, export/delete flows, DPDP Act/privacy review and clear consent.
- Subscription entitlements enforced server-side; payment webhook signature verification before enabling premium storage.

## Terraform
`infra/main.tf` is only an S3 starter. Extend it with VPC, ECS, ALB, RDS, Cognito, CloudFront, SQS, KMS, IAM, Secrets Manager, DNS, alarms and backup policies before deployment. Do not commit AWS credentials. Use task roles in AWS.

## Current implementation boundaries
This repository is a Phase 1 engineering starter, not a production-certified application. It contains runnable web/mobile scaffolding, group and invitation token APIs, and direct S3 upload flow. Authentication, real email/SMS invitation delivery, complete background auto-upload, EXIF worker, native camera capture, full map, billing, automated expiry, and AWS production deployment remain planned integrations. Build/test in your environment and complete the security checklist before handling personal media.
