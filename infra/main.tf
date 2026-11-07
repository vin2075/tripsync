terraform {
 required_version = ">= 1.6.0"
 required_providers { aws = { source = "hashicorp/aws", version = "~> 5.0" } }
}
provider "aws" { region = var.aws_region }
variable "aws_region" { type = string; default = "ap-south-1" }
variable "project" { type = string; default = "tripsync" }
variable "media_bucket_name" { type = string; description = "Globally unique private S3 bucket name" }
resource "aws_s3_bucket" "media" { bucket = var.media_bucket_name; force_destroy = false }
resource "aws_s3_bucket_public_access_block" "media" { bucket = aws_s3_bucket.media.id; block_public_acls = true; block_public_policy = true; ignore_public_acls = true; restrict_public_buckets = true }
resource "aws_s3_bucket_versioning" "media" { bucket = aws_s3_bucket.media.id; versioning_configuration { status = "Enabled" } }
resource "aws_s3_bucket_server_side_encryption_configuration" "media" { bucket = aws_s3_bucket.media.id; rule { apply_server_side_encryption_by_default { sse_algorithm = "aws:kms" } bucket_key_enabled = true } }
resource "aws_s3_bucket_cors_configuration" "media" { bucket = aws_s3_bucket.media.id; cors_rule { allowed_methods = ["PUT", "GET", "HEAD"] allowed_origins = ["http://localhost:5173"] allowed_headers = ["*"] expose_headers = ["ETag"] max_age_seconds = 3000 } }
output "media_bucket" { value = aws_s3_bucket.media.bucket }
