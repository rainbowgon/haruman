 echo "\n****** Blue Health Check & Build ******"

if curl -s "http://3.34.135.50:8081" > /dev/null
then
    deployment_target_port=8082
    deployment_target=green
else
    deployment_target_port=8081
    deployment_target=blue
fi

echo [BE] Green Build Docker Image!
docker build -t server ./backend
echo [BE] Green Run Docker Container!
docker run -dp ${deployment_target_port}:8081 --name haruman_server_${deployment_target} server



echo "\n****** Continuous Green Health Check ******"

for retry_count in \$(seq 10)
do
    if curl -s "http://3.34.135.50:${deployment_target_port}" > /dev/null
    then
        echo "Health check success ✅"
        break
    fi

    if [ $retry_count -eq 10 ]
    then
    echo "Health check failed ❌"
    exit 1
    fi

    echo "The server is not alive yet. Retry health check in 10 seconds..."
    sleep 10
done



echo "\n****** Nginx Proxy Redirect & Kill Blue ******"

pwd
cd backend
pwd
ls
ssh -o StrictHostKeyChecking=no -i J9A607T.pem ubuntu@j9a607.p.ssafy.io "echo 'set \\http://3.34.135.50:8081; http://3.34.135.50:${deployment_target_port};' > /etc/nginx/conf.d/service-url.inc && service nginx reload"
echo "Switch the reverse proxy direction of nginx to http://3.34.135.50:${deployment_target_port} 🔄"

if [ "${deployment_target}" == blue ]
then
    ssh root@${green_ip} "fuser -s -k 8081/tcp"
else
    ssh root@${blue_ip} "fuser -s -k 8081/tcp"
fi
echo "Kill the process on the opposite server."