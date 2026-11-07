# TripSync Mobile (Expo)

## Run
```bash
cd mobile
cp .env.example .env
npm install
npx expo start
```
Set `EXPO_PUBLIC_API_URL` to your backend. Android emulator uses `http://10.0.2.2:8080`; iOS simulator can use `http://localhost:8080`; physical devices need your development machine LAN IP.

## Current behavior
- Create trips through the Phase 1 API.
- Select multiple photos/videos and upload through presigned S3 PUT URLs.
- Request photo-library permission before enabling the auto-upload preference.

## Native background upload implementation boundary
The auto-upload switch in this starter saves user intent and requests media permission. It does **not** silently upload the camera roll. Complete the production adapter using MediaLibrary change listeners + a durable upload queue and WorkManager on Android; on iOS use PhotoKit limited-library authorization and background URLSession. OS scheduling is opportunistic, not guaranteed immediately. Require explicit per-group opt-in, Wi-Fi/cellular settings, duplicate detection, pause/resume, and visible queue controls. Never upload a user's whole library without clear consent.
