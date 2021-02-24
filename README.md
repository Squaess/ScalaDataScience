In order work with the **MagicTheGathering** part of this project
you will need to download and extract the data from this [**Kaggle**](https://www.kaggle.com/mylesoneill/magic-the-gathering-cards) competition.

For running scala notebook I'm using `jupyter lab` with `amonite`. And especially helpful is this [docker](https://almond.sh/docs/try-docker) image.

I'm reusing the same container for development and mounting the downloaded files to the docker container using the following command:
```
docker run -it --entrypoint="" -v $PWD:/home/jovyan/work -p 8888:8888 almondsh/almond:latest bash
```