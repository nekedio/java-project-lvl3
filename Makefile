path = $(shell pwd)

run: install-dist run-dist

install-dist:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

test:
	./gradlew test

report: test
	./gradlew jacocoTestReport
	@echo "\n\nOpen the following file in any browser:"
	@echo "\033[34mfile://${path}/build/jacocoHtml/index.html\033[0m"
	@echo "---------------------------------------------------------------------------------------------------"
	@w3m -dump file://${path}/build/jacocoHtml/index.html
	@echo "---------------------------------------------------------------------------------------------------"
