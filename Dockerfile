FROM clojure:openjdk-17-lein 
COPY . /usr/src/app
WORKDIR /usr/src/app
CMD lein run
