FROM library/node
# Install Bower & Grunt
RUN npm install -g bower grunt-cli grunt && echo '{ "allow_root": true }' > /root/.bowerrc
WORKDIR /data/app
ADD . /data/app
RUN npm install && bower install
EXPOSE 9000:9000
CMD ["grunt", "serve"]