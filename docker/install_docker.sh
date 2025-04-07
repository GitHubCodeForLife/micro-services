# Update the apt package index:
sudo apt-get update

# Install packages to allow apt to use a repository over HTTPS:
sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

# Add Docker’s official GPG key:
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# Set up the stable repository:
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

# Update the apt package index:
sudo apt-get update

# Install the latest version of Docker CE and containerd:
sudo apt-get install -y docker-ce docker-ce-cli containerd.io

# Verify that Docker CE is installed correctly by running the hello-world image.
sudo docker run hello-world

