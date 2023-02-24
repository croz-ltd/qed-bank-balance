CONTAINER_REGISTRY=harbor.croz.net
REGISTRY_PROJECT=devops-course
IMAGE_TAG=latest
docker build -t $(./image_name.sh) .
