# Wyatt

Lambda service triggered by a monthly CloudWatch event which fetches your Verizon bill in JSON format and stores it in S3. On upload, the S3 bucket publishes a notification to SNS topic `wyatt-prd`.

I have a separate, private service which triggers off of this notification event and splits the bill with my family.

Named after [Ben Wyatt](https://en.wikipedia.org/wiki/Ben_Wyatt_(Parks_and_Recreation)), the auditor from "Parks and Rec" played by Adam Scott.

![Ben Wyatt](img/wyatt.webp)

# Localstack

This project uses [localstack](https://github.com/localstack/localstack) for testing. 

A few helpful snippets for working with it:

- Create s3 bucket: `aws --endpoint-url=http://localhost:4572 s3 mb s3://wyatt-bills`
- Upload file: `aws --endpoint-url=http://localhost:4572 s3 cp src/test/resources/test_bill.json s3://wyatt-bills`
- List buckets: `aws --endpoint-url=http://localhost:4572 s3 ls`
- List files in a bucket: `aws --endpoint-url=http://localhost:4572 s3 ls s3://wyatt-bills`
- Get a file: `aws --endpoint-url=http://localhost:4572 s3 cp s3://wyatt-bills/bill.json /tmp/bill.json`
- Delete bucket: `aws --endpoint-url=http://localhost:4572 s3 rb s3://wyatt-bills --force`

# Reference

- https://www.npmjs.com/package/serverless-plugin-chrome
