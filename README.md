# Tweet Sentiment Package
mvn package
aws cloudformation package --template-file sam.yml --profile=cjd --s3-bucket cjdconsulting-sam --output-template-file packaged.yml


# Tweet Sentiment Deploy
aws cloudformation --profile=cjd deploy --template-file packaged.yml --stack-name tweet-sentiment --region=us-west-2 --capabilities CAPABILITY_IAM


