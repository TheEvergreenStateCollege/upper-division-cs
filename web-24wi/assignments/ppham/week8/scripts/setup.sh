#/usr/bin/env bash 

set -x

if [ -z "$(which autossh)" ]; then 
  sudo apt update
  sudo apt install -y autossh jq
fi

TOKEN=$(cat .env | grep "TOKEN" | cut -d "'" -f 2)

if [ -e "./.env" ]; then 
  echo "You've already renamed your .env file."
else
  echo "Renaming your .env file, be sure to change DATABASE_URL accordingly."
  cp .env.RENAME_ME .env
fi

if [ -e "./id_ecdsa.pub" ]; then 
  echo "You've already retrieved a public key."
else
  curl -s -X GET https://indira.arcology.builders/api/ssh-key \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer ${TOKEN}" \
    -o id_ecdsa.pub
fi

# Commands to test your API

# Get an SSH keypair after logging in 
autossh -M 30001 -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 -i ./id_ecdsa ubuntu@indira.arcology.builders 

npm i -g pnpm
pnpm setup
source ~/.bashrc; pnpm i -g ts-node
pnpm i

ts-node src/index.ts
