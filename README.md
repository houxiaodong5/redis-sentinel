项目搭建参考博客：HTTPS：//www.cnblogs.com/ruiati/p/6374152.html
具体配置见压缩包中的redis.windows.conf,redis.windows7001.conf,redis.windows7002.conf，
sentinel配置见sentinel.conf,sentinel7001.conf,sentinel7002.conf
启动步骤：
      1.启动redis:redis-server redis.windows.conf
      2.启动sentinel:redis-server sentinel.conf --sentinel
      3.分别启动3个redis和3个sentinel
      注：使用redis-cli -h 127.0.0.1 -p 7000 连接至redis后,
             使用命令 info replication 可查看master及slave各个节点的信息
             测试新发现，当master节点down掉之后，slave会自动切换未master;当之前的master恢复重启后，会变成slave节点。
      尚未解决的问题：jedisSentinelPool.getResource()拿到的信息，始终是master的信息，即读写均显示master节点的信息。why?  


