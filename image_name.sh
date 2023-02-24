CONTAINER_REGISTRY=harbor.croz.net
REGISTRY_PROJECT=devops-course
IMAGE_TAG=latest
echo "${CONTAINER_REGISTRY}/${REGISTRY_PROJECT}/djajcevic/$(basename "$PWD"):${IMAGE_TAG}"
