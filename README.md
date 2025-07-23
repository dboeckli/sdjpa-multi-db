# Spring Data JPA Multiple Database Project

This repository contains source code examples to support my course Spring Data JPA and Hibernate Beginner to Guru

## Multi-Database Configuration

This project is configured to work with three separate databases:

1. Credit Card Holder Database
2. Credit Card PAN (Primary Account Number) Database
3. Credit Card Transaction Database

Each database is configured independently, allowing for separate connection details, migration scripts, and entity management.

## Flyway

To enable Flyway in the MySQL profile, override the following properties when starting the application:
- `spring.flyway.enabled = true`
- `spring.docker.compose.file = compose-mysql.yaml`

This profile starts MySQL on port 3306 using the Docker Compose file `compose-mysql-.yaml`.

## Docker

Docker Compose file initially use the startup script located in `src/scripts`. These scripts create the database and users.

## Kubernetes

### Generate Config Map for mysql init script

When updating 'src/scripts/init-mysql-mysql.sql', apply the changes to the Kubernetes ConfigMap:
```bash
kubectl create configmap mysql-init-script --from-file=init.sql=src/scripts/init-mysql.sql --dry-run=client -o yaml | Out-File -Encoding utf8 k8s/mysql-init-script-configmap.yaml
```

### Deployment with Helm

Be aware that we are using a different namespace here (not default).

Go to the directory where the tgz file has been created after 'mvn install'
```powershell
cd target/helm/repo
```

unpack
```powershell
$file = Get-ChildItem -Filter sdjpa-multi-db-v*.tgz | Select-Object -First 1
tar -xvf $file.Name
```

install
```powershell
$APPLICATION_NAME = Get-ChildItem -Directory | Where-Object { $_.LastWriteTime -ge $file.LastWriteTime } | Select-Object -ExpandProperty Name
helm upgrade --install $APPLICATION_NAME ./$APPLICATION_NAME --namespace sdjpa-multi-db --create-namespace --wait --timeout 5m --debug
```

show logs
```powershell
kubectl get pods -n sdjpa-multi-db
```

replace $POD with pods from the command above
```powershell
kubectl logs $POD -n sdjpa-multi-db --all-containers
```

Show Endpoints
```powershell
kubectl get endpoints -n sdjpa-multi-db
```

test
```powershell
helm test $APPLICATION_NAME --namespace sdjpa-multi-db --logs
```

status
```powershell
helm status $APPLICATION_NAME --namespace sdjpa-multi-db
```

uninstall
```powershell
helm uninstall $APPLICATION_NAME  --namespace sdjpa-multi-db
```

delete all
```powershell
kubectl delete all --all -n sdjpa-multi-db
```

create busybox sidecar
```powershell
kubectl run busybox-test --rm -it --image=busybox:1.36 --namespace=sdjpa-multi-db --command -- sh
```

You can use the actuator rest call to verify via port 30080

## Running the Application
1. Choose between h2 or mysql for database schema management. (you can use one of the preconfigured intellij runners)
2. Start the application with the appropriate profile and properties.
3. The application will use Docker Compose to start MySQL and apply the database schema changes.