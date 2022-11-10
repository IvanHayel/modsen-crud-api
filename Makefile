VERSION=0.0.1
USR=#External database username here
PWD=#External database password here
JDBC=#External database jdbc url here

docker-build:
	docker build -t modsen-crud-api:${VERSION} .

docker-run:
	docker run -e DB_USERNAME=${USR} -e DB_PASSWORD=${PWD} -e DB_URL=${JDBC} --name modsen-crud-api-container -p 8080:8080 modsen-crud-api:${VERSION}
