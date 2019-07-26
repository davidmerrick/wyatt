# Wyatt

Service to fetch your Verizon bill every month and store it in S3.

Named after [Ben Wyatt](https://en.wikipedia.org/wiki/Ben_Wyatt_(Parks_and_Recreation)), the auditor from Parks and Rec played by Adam Scott.

# Localstack

Localstack's web interface is at http://localhost:8080.

Create s3 bucket:
`aws --endpoint-url=http://localhost:4572 s3 mb s3://wyatt-bills`

Upload file:
`aws --endpoint-url=http://localhost:4572 s3 cp src/test/resources/test_bill.json s3://wyatt-bills`

List buckets: 
`aws --endpoint-url=http://localhost:4572 s3 ls`

List files in a bucket: 
`aws --endpoint-url=http://localhost:4572 s3 ls s3://wyatt-bills`

Delete bucket:
`aws --endpoint-url=http://localhost:4572 s3 rb s3://wyatt-bills --force`