mvn clean package

cd sender
cp target/sender-jar-with-dependencies.jar ../clientapp/target
cd ..

cd receiver
cp target/receiver-jar-with-dependencies.jar ../serverapp/target
cd ..

docker-compose up --build