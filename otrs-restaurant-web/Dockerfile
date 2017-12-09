FROM library/node
# Install Bower & Grunt
RUN npm install -g bower grunt-cli grunt && echo '{ "allow_root": true }' > /root/.bowerrc
WORKDIR /data
ADD . /data/app
RUN cd /data/app && npm install && bower install
RUN cd /data/app && npm install grunt
EXPOSE 8084:8084
ENTRYPOINT ["grunt", "serve"]