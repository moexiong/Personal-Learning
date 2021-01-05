### Nmap的探索之路，一步一jio印
#### Nmap定义
官网：`https://nmap.org`
> Nmap（"网络映射器"）是一个免费的开源（许可证）用于网络发现和安全审计的实用程序。许多系统和网络管理员还发现它对网络库存，管理服务升级计划以及监控主机或服务正常运行时间等任务非常有用。Nmap以新颖的方式使用原始IP数据包来确定网络上可用的主机，这些主机提供的服务（应用程序名称和版本），运行的操作系统（和操作系统版本），数据包过滤器/防火墙的类型正在使用中，还有许多其他特性。它旨在快速扫描大型网络，但对单个主机可以正常工作。Nmap可在所有主要计算机操作系统上运行，官方二进制包可用于Linux，Windows和Mac OS X.除了经典的命令行Nmap可执行文件外，Zenmap），一种灵活的数据传输，重定向和调试工具（Ncat），一种用于比较扫描结果的工具（Ndiff），以及一个数据包生成和响应分析工具（Nping）。

> Nmap（“ 网络映射器 ”）是一种用于网络探索和安全审计的开源工具。它旨在快速扫描大型网络，虽然它可以很好地对抗单个主机。Nmap以新颖的方式使用原始IP数据包来确定网络上可用的主机，这些主机提供的服务（应用程序名称和版本），运行的操作系统（和操作系统版本），数据包过滤器/防火墙的类型正在使用中，还有许多其他特性。虽然Nmap通常用于安全审核，但许多系统和网络管理员发现它对日常任务非常有用，例如网络清单，管理服务升级计划以及监控主机或服务的正常运行时间。

```
rpm -vhU https://nmap.org/dist/nmap-7.80-1.x86_64.rpm
rpm -vhU https://nmap.org/dist/zenmap-7.80-1.noarch.rpm
rpm -vhU https://nmap.org/dist/ncat-7.80-1.x86_64.rpm
rpm -vhU https://nmap.org/dist/nping-0.7.80-1.x86_64.rpm
```
#### Nmap命令参数总览（不全，粗略介绍）
`nmap --help` 显示Nmap命令的相应帮助信息

> 结果如下：

```
用法：nmap [扫描类型] [选项] {目标规范}
目标规格：
  可以传递主机名，IP地址，网络等。
  例如：scanme.nmap.org，microsoft.com / 24,192.168.0.1; 10.0.0-255.1-254
  -iL <inputfilename>：从主机/网络列表输入
  -iR <num hosts>：选择随机目标
  --exclude <host1 [，host2] [，host3]，...>：排除主机/网络
  --excludefile <exclude_file>：从文件中排除列表
主机发现：
  -sL：列表扫描 - 只列出要扫描的目标
  -sn：Ping扫描 - 禁用端口扫描
  -Pn：将所有主机视为在线 - 跳过主机发现
  -PS / PA / PU / PY [端口列表]：给定端口的TCP SYN / ACK，UDP或SCTP发现
  -PE / PP / PM：ICMP回送，时间戳和网络掩码请求发现探测
  -PO [协议列表]：IP协议Ping
  -n / -R：从不进行DNS解析/始终解析[默认：有时]
  --dns-servers <serv1 [，serv2]，...>：指定自定义DNS服务器
  --system-dns：使用OS的DNS解析器
  --traceroute：每个主机的跟踪跳转路径
扫描技术：
  -sS / sT / sA / sW / sM：TCP SYN / Connect（）/ ACK / Window / Maimon扫描
  -sU：UDP扫描
  -sN / sF / sX：TCP Null，FIN和Xmas扫描
  --scanflags <flags>：自定义TCP扫描标志
  -sI <zombie host [：probeport]>：空闲扫描
  -sY / sZ：SCTP INIT / COOKIE-ECHO扫描
  -sO：IP协议扫描
  -b <FTP中继主机>：FTP反弹扫描
港口规格和扫描令：
  -p <端口范围>：仅扫描指定的端口
    例如：-p22; -p1-65535; -p U：53,111,137，T：21-25,80,139,8080，S：9
  --exclude-ports <端口范围>：从扫描中排除指定的端口
  -F：快速模式 - 扫描比默认扫描更少的端口
  -r：连续扫描端口 - 不要随机化
  --top-ports <number>：扫描<number>最常见的端口
  --port-ratio <ratio>：扫描端口比<ratio>更常见
服务/版本检测：
  -sV：探测开放端口以确定服务/版本信息
  --version-intensity <level>：从0（亮）到9（尝试所有探针）设置
  --version-light：限制最可能的探针（强度2）
  --version-all：尝试每一个探针（强度9）
  --version-trace：显示详细的版本扫描活动（用于调试）
脚本扫描：
  -sC：相当于--script = default
  --script = <Lua scripts>：<Lua scripts>是逗号分隔的列表
           目录，脚本文件或脚本类别
  --script-args = <n1 = v1，[n2 = v2，...]>：为脚本提供参数
  --script-args-file = filename：在文件中提供NSE脚本args
  --script-trace：显示发送和接收的所有数据
  --script-updatedb：更新脚本数据库。
  --script-help = <Lua scripts>：显示有关脚本的帮助。
           <Lua scripts>是以逗号分隔的脚本文件列表或
           脚本的类别。
操作系统检测：
  -O：启用OS检测
  --osscan-limit：将OS检测限制为有希望的目标
  --osscan-guess：更积极地猜测操作系统
时间和性能：
  采用<time>的选项以秒为单位，或追加'ms'（毫秒），
  该值的's'（秒），'m'（分钟）或'h'（小时）（例如30m）。
  -T <0-5>：设置时序模板（越高越快）
  --min-hostgroup / max-hostgroup <size>：并行主机扫描组大小
  --min-parallelism / max-parallelism <numprobes>：探测并行化
  --min-rtt-timeout / max-rtt-timeout / initial-rtt-timeout <time>：指定
      探测往返时间。
  --max-retries <tries>：端口扫描探测重传的上限数量。
  --host-timeout <time>：在此之后放弃目标
  --scan-delay /  -  max-scan-delay <time>：调整探针之间的延迟
  --min-rate <number>：发送数据包的速度不低于每秒<number>
  --max-rate <number>：发送数据包的速度不超过<number>每秒
防火墙/ IDS逃脱和防御：
  -F; --mtu <val>：分段数据包（可选择w /给定MTU）
  -D <decoy1，decoy2 [，ME]，...>：用诱饵披露扫描
  -S <IP_Address>：欺骗源地址
  -e <iface>：使用指定的接口
  -g /  -  source-port <portnum>：使用给定的端口号
  --proxies <url1，[url2]，...>：通过HTTP / SOCKS4代理的中继连接
  --data <hex string>：为发送的数据包附加自定义有效负载
  --data-string <string>：为发送的数据包附加自定义ASCII字符串
  --data-length <num>：将随机数据附加到已发送的数据包
  --ip-options <options>：发送带有指定ip选项的数据包
  --ttl <val>：设置IP生存时间字段
  --spoof-mac <mac address / prefix / vendor name>：欺骗你的MAC地址
  --badsum：使用伪TCP / UDP / SCTP校验和发送数据包
OUTPUT：
  -oN / -oX / -oS / -oG <file>：正常输出扫描，XML，s | <rIpt kIddi3，
     和Grepable格式，分别为给定的文件名。
  -oA <basename>：一次输出三种主要格式
  -v：增加详细级别（使用-vv或更高级别以获得更好的效果）
  -d：提高调试级别（使用-dd或更多以获得更好的效果）
  --reason：显示端口处于特定状态的原因
  --open：仅显示打开（或可能打开）的端口
  --packet-trace：显示发送和接收的所有数据包
  --iflist：打印主机接口和路由（用于调试）
  --append-output：附加到而不是clobber指定的输出文件
  --resume <filename>：恢复中止扫描
  --stylesheet <path / URL>：用于将XML输出转换为HTML的XSL样式表
  --webxml：来自Nmap.Org的参考样式表，用于更多可移植的XML
  --no-stylesheet：防止与XML输出相关联的XSL样式表
MISC：
  -6：启用IPv6扫描
  -A：启用操作系统检测，版本检测，脚本扫描和跟踪路由
  --datadir <dirname>：指定自定义Nmap数据文件位置
  --send-eth /  -  send-ip：使用原始以太网帧或IP数据包发送
  --privileged：假设用户具有完全特权
  --unprivileged：假设用户缺少原始套接字权限
  -V：打印版本号
  -h：打印此帮助摘要页面。
例子：
  nmap -v -A scanme.nmap.org
  nmap -v -sn 192.168.0.0/16 10.0.0.0/8
  nmap -v -iR 10000 -Pn -p 80
```
#### Nmap功能详细介绍
##### 主机扫描
###### 域名和IP寻址
> Nmap命令行上**不是选项（或选项参数）的所有内容都被视为目标主机规范**。最简单的情况是指定扫描的目标IP地址或主机名。

> 当主机名作为目标提供时，它将被解析通过域名系统（DNS）确定要扫描的IP地址。如果名称解析为多个IP地址，则**仅扫描第一个IP地址**。要使Nmap扫描所有已解析的地址而不是仅扫描第一个地址，请使用该--resolve-all选项。

`nmap -v -A scanme.nmap.org` 此命令会去扫描域名下的主机端口，跃点路径等状态。
###### CIDR风格寻址
> 在域名或者IP的后方使用/+数字IPv4（0，8，16，24，32），IPv6（0，8，...，128），将IP转换2进制后，以每8位2进制作为确定位数寻址。

> 参数`192.168.1.10/24` 在IPv4的情况下，固定二进制IP下的前24位地址位，扫描192.168.1.0~192.168.1.255之间的256个主机。

> 参数`scanme.nmap.org/16` 对应域名所在的公网IP处于64.13.134.52处，地址为IPv4，固定前16位地址位，扫描64.13.0.0~64.13.255.255之间的65536个IP地址。

`nmap -v -sn 192.168.0.0/16` 此命令会ping扫描192.168.0.0~192.168.255.255之间的所有IP地址。
###### 八位位组范围寻址
> 八位位组范围指的是在二进制表示IP下，每8位可以使用逗号`,`或`-`分隔开。`,`里面列举的是一个IP列表（1,3,4,10）有4个值，**中间不可有空格，制表符**等。`-`里面列举的是一个IP范围（1-10）有10个值，**中间不可有空格，制表符**等。两者可以在一个IP地址中混合使用，单独使用`-`的作用与`0-255`相同。

> 参数`192.168.1-254.10-233` 此命令会去扫描192.168.1.0~192.168.254.255之间所有以.10~.233结尾的IP地址。

> 参数`192.168.3-5,7.1` 此命令会去扫描192.168.3.1，192.168.4.1，192.168.5.1，192.168.7.1这4个IP地址。

> 参数`192.168.1,3.-` 此命令会去扫描192.168.1.0~192.168.1.255和192.168.3.0~192.168.3.255之间的所有IP地址。
###### 补充说明
> Nmap在命令行上接受多个主机规范，它们不需要是同一类型。

`nmap scanme.nmap.org 192.168.0.0/8 10.0.0,1,3-7.-` 此命令会按照期望的去依次扫描所有的IP地址（太多了，列不来了）。
#### Nmap参数详细介绍

