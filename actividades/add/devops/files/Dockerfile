FROM debian:8

MAINTAINER David Vargas version 1.0

RUN apt-get update
RUN apt-get install -y nginx
RUN apt-get install -y vim

COPY server.sh /root
RUN chmod +x /root/server.sh

EXPOSE 80

CMD ["/root/server.sh"]
