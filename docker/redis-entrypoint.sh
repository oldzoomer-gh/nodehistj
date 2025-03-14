#!/bin/sh
mkdir -p /usr/local/etc/redis &&
echo "bind 0.0.0.0" > /usr/local/etc/redis/redis.conf &&
echo "requirepass $REDIS_PASSWORD" >> /usr/local/etc/redis/redis.conf &&
echo "appendonly yes" >> /usr/local/etc/redis/redis.conf &&
echo "appendfsync everysec" >> /usr/local/etc/redis/redis.conf &&
echo "user default on nopass ~* +@all" > /usr/local/etc/redis/users.acl &&
exec "$@"