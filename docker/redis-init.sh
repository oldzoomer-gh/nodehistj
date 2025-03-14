#!/bin/sh
mkdir -p /usr/local/etc/redis
{
  echo "bind 0.0.0.0"
  echo "requirepass $REDIS_PASSWORD"
  echo "appendonly yes"
  echo "appendfsync everysec"
} > /usr/local/etc/redis/redis.conf
echo "user default on nopass ~* +@all" > /usr/local/etc/redis/users.acl
exec redis-server /usr/local/etc/redis/redis.conf --aclfile /usr/local/etc/redis/users.acl