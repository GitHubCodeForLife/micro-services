#uninstall docker
sudo apt-get purge -y docker-ce docker-ce-cli containerd.io
sudo apt-get autoremove -y
sudo rm -rf /var/lib/docker
sudo rm -rf /etc/docker