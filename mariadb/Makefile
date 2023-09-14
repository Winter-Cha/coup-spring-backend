# Makefile
# 사용방법 ex)

# make up
# 	docker-compose -f docker-compose.yml -f docker-compose.dev.yml up
# make down
# 	docker-compose -f docker-compose.yml -f docker-compose.dev.yml down -rmi

# 운영환경에서의 빌드 방법 
# make up PROFILE=prd

PROFILE := dev
COMPOSE_FILES := -f docker-compose.${PROFILE}.yml

up:
	# 실행된 명령어
	# docker-compose ${COMPOSE_FILES} up -d
	@docker-compose ${COMPOSE_FILES} up -d

down:
	@docker-compose ${COMPOSE_FILES} down --rmi all

shell:
	@docker exec -it Co-up-MariaDB /bin/bash

# 변수 테스트 용 명령어
echo:
	# ${COMPOSE_FILES} ${PROFILE}
	@echo ${COMPOSE_FILES}

