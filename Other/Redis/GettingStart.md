# Redis

## 一.  Redis的诞生

​		08 年的时候有一个意大利西西里岛的小伙子，笔名 antirez（http://invece.org/），创建 了一个访客信息网站 LLOOGG.COM。有的时候我们需要知道网站的访问情况，比如 客的 IP、操作系统、浏览器、使用的搜索关键词、所在地区、访问的网页地址等等。在 国内，有很多网站提供了这个功能，比如 CNZZ，百度统计，国外也有谷歌的 Google Analytics。我们不用自己写代码去实现这个功能，只需要在全局的 footer 里面嵌入一段 JS 代码就行了，当页面被访问的时候，就会自动把访客的信息发送到这些网站统计的服 务器，然后我们登录后台就可以查看数据了。

​		LLOOGG.COM 提供的就是这种功能，它可以查看最多 10000 条的最新浏览记录。 这样的话，它需要为每一个网站创建一个列表（List），不同网站的访问记录进入到不同 的列表。如果列表的长度超过了用户指定的长度，它需要把最早的记录删除（先进先出）

<img src="https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220904115418312.png" alt="image-20220904115418312" style="zoom: 33%;" />

​		当 LLOOGG.COM 的用户越来越多的时候，它需要维护的列表数量也越来越多，这 种记录最新的请求和删除最早的请求的操作也越来越多。LLOOGG.COM 最初使用的数 据库是 MySQL，可想而知，因为每一次记录和删除都要读写磁盘，因为数据量和并发量 太大，在这种情况下无论怎么去优化数据库都不管用了。 

​		考虑到最终限制数据库性能的瓶颈在于磁盘，所以 antirez 打算放弃磁盘，自己去实 现一个具有列表结构的数据库的原型，把数据放在内存而不是磁盘，这样可以大大地提 升列表的 push 和 pop 的效率。antirez 发现这种思路确实能解决这个问题，所以用 C 语言重写了这个内存数据库，并且加上了持久化的功能，09 年，Redis 横空出世了。从最 开始只支持列表的数据库，到现在支持多种数据类型，并且提供了一系列的高级特性， Redis 已经成为一个在全世界被广泛使用的开源项目。 

​		为什么叫 REDIS 呢？它的全称是 REmote DIctionary Service，直接翻译过来是远 程字典服务。 

​		从 Redis 的诞生历史我们看到了，在某些场景中，关系型数据库并不适合用来存储 我们的 Web 应用的数据。那么，关系型数据库和非关系型数据库，或者说 SQL 和 NoSQL， 到底有什么不一样呢？

## 二.  Redis定位与特性

​		在绝大部分时候，我们都会首先考虑用关系型数据库来存储我们的数据，比如 SQLServer，Oracle，MySQL 等等。 

### 关系型数据库的特点： 

- 1、它以表格的形式，基于行存储数据，是一个二维的模式。 
- 2、它存储的是结构化的数据，数据存储有固定的模式（schema），数据需要适应 表结构。 
- 3、表与表之间存在关联（Relationship）。
- 4、大部分关系型数据库都支持 SQL（结构化查询语言）的操作，支持复杂的关联查 询。 
- 5、通过支持事务（ACID 酸）来提供严格或者实时的数据一致性。 

​		但是使用关系型数据库也存在一些限制，比如

- 1、要实现扩容的话，只能向上（垂直）扩展，比如磁盘限制了数据的存储，就要扩 大磁盘容量，通过堆硬件的方式，不支持动态的扩缩容。水平扩容需要复杂的技术来实 现，比如分库分表。 
- 2、表结构修改困难，因此存储的数据格式也受到限制。 
- 3、在高并发和高数据量的情况下，我们的关系型数据库通常会把数据持久化到磁盘， 基于磁盘的读写压力比较大。

​		为了规避关系型数据库的一系列问题，我们就有了非关系型的数据库，我们一般把 它叫做“non-relational”或者“Not Only SQL”。NoSQL 最开始是不提供 SQL 的数 据库的意思，但是后来意思慢慢地发生了变化。

### 非关系型数据库的特点： 

- 1、存储非结构化的数据，比如文本、图片、音频、视频。 
- 2、表与表之间没有关联，可扩展性强。 
- 3、保证数据的最终一致性。遵循 BASE（碱）理论。 Basically Available（基本 可用）； Soft-state（软状态）； Eventually Consistent（最终一致性）。 
- 4、支持海量数据的存储和高并发的高效读写。 
- 5、支持分布式，能够对数据进行分片存储，扩缩容简单。



### 常见非关系型的类型： 

- 1、KV 存储，用 Key Value 的形式来存储数据。比较常见的有 Redis 和 MemcacheDB
- 2、文档存储，MongoDB。 
- 3、列存储，HBase。 
- 4、图存储，这个图（Graph）是数据结构，不是文件格式。Neo4j。 
- 5、对象存储。 
- 6、XML 存储等等等等。

> NewSQL 结合了 SQL 和 NoSQL 的特性（例如 PingCAP 的 TiDB）

## 三.  Redis特性

> 官网介绍：https://redis.io/topics/introduction 
>
> 中文网站：http://www.redis.cn 

​		硬件层面有 CPU 的缓存；浏览器也有缓存；手机的应用也有缓存。我们把数据缓存 起来的原因就是从原始位置取数据的代价太大了，放在一个临时位置存储起来，取回就 可以快一些。

 Redis 的特性： 

- 1）更丰富的数据类型 
- 2）进程内与跨进程；单机与分布式 
- 3）功能丰富：持久化机制、过期策略 
- 4）支持多种编程语言 
- 5）高可用，集群

## 四.  Redis 安装启动

### 1.  安装

#### ①  Linux 安装 参考：

 CentOS7 安装 Redis 单实例 https://gper.club/articles/7e7e7f7ff7g5egc4g6b 

Docker 安装 RabbitMQ 集群 https://gper.club/articles/7e7e7f7ff7g5egc5g6c 

主要是注意配置文件几处关键内容（后台启动、绑定 IP、密码）的修改，配置别名 

#### ②  Windows 服务端安装

​		Redis 作者没有为 Windows 编写 Redis 服务端，微软自行编写了一个 Redis 服务端， 可用于基本的测试和学习。 https://github.com/MicrosoftArchive/redis/tag

### 2.  启动

src 目录下，直接启动

```shell
./redis-server
```

后台启动（指定配置文件） 

1、redis.conf 

```shell
daemonize yes
bind 0.0.0.0
```

2、启动 Redis

```shell
redis-server /usr/local/soft/redis-5.0.5/redis.conf
```

总结：redis 的参数可以通过三种方式配置，一种是 redis.conf，一种是启动时--携带的参数，一种是 config set。



### 基本操作

默认有 16 个库（0-15），可以在配置文件中修改，默认使用第一个 db0

```shell
databases 16
```

因为没有完全隔离，不像数据库的 database，不适合把不同的库分配给不同的业务 使用

切换数据库

```shell
select 0
```

清空当前数据

```shell
flushdb
```

清空所有数据库

```shell
flushall
```

​		Redis 是字典结构的存储方式，采用 key-value 存储。key 和 value 的最大长度限制 是 512M（来自官网 https://redis.io/topics/data-types-intro/）。 键的基本操作。 命令参考：http://redisdoc.com/index.htm

存值 

```shell 
set lan 2673 
```

取值 

```shell
get lan
```

查看所有键

```shell
keys * 
```

获取键总数

```shell
dbsize
```

查看键是否存在

```shell
exists lan 
```

删除键

```shell
del lan jack 
```

重命名键

```shell
rename lan pengyuyan 
```

查看类型

```shell
type lan
```

> Redis 一共有几种数据类型？（注意是数据类型不是数据结构） 
>
> 官网：https://redis.io/topics/data-types-intro 
>
> String、Hash、Set、List、Zset、Hyperloglog、Geo、Streams

## 五.  Redis 基本数据类型

> ​		最基本也是最常用的数据类型就是 String。set 和 get 命令就是 String 的操作命令。 为什么叫 
>
> Binary-safe strings 呢

### String 字符串

存储类型

可以用来存储字符串、整数、浮点数。

 操作命令 设置多个值（批量操作，原子性)

```shell
mset qingshan 2673 jack 666 
```

设置值，如果 key 存在，则不成功

```shell
setnx qingshan
```

基于此可实现分布式锁。用 del key 释放锁。 

但如果释放锁的操作失败了，导致其他节点永远获取不到锁，怎么办？ 

加过期时间。单独用 expire 加过期，也失败了，无法保证原子性，怎么办？多参数 

```shell
set key value [expiration EX seconds|PX milliseconds][NX|XX]
```

 使用参数的方式

```shell
set lock1 1 EX 10 NX
```

 （整数）值递增

```shell
incr qingshan incrby qingshan 100
```

 （整数）值递减

```shell
decr qingshan decrby qingshan 100
```

  浮点数增量

```shell
set f 2.6 
incrbyfloat f 7.3
```

  获取多个值

```shell
mget qingshan jack
```

获取值长度

```shell 
strlen qingshan 
```

字符串追加内容

```shell
append qingshan good
```

 获取指定范围的字符

```shell 
getrange qingshan 0 8
```

#### 1.  存储（实现）原理

 #####   ①  数据模型

​		 set hello word 为例，因为 Redis 是 KV 的数据库，它是通过 hashtable 实现的（我 们把这个叫做外层的哈希）。所以每个键值对都会有一个 dictEntry（源码位置：dict.h）， 里面指向了 key 和 value 的指针。next 指向下一个 dictEntry。

```C
typedef struct dictEntry {
    void *key; /* key 关键字定义 */
    union {
        void *val; uint64_t u64; /* value 定义 */
        int64_t s64; double d;
} v;
	struct dictEntry *next; /* 指向下一个键值对节点 */
} dictEntry;
```



​		key 是字符串，但是 Redis 没有直接使用 C 的字符数组，而是存储在自定义的 SDS 中。

​		value 既不是直接作为字符串存储，也不是直接存储在 SDS 中，而是存储在 redisObject 中。实际上五种常用的数据类型的任何一种，都是通过 redisObject 来存储 的。

##### ②  redisObject

redisObject 定义在 src/server.h 文件中。

```c++
typedef struct redisObject {
    unsigned type:4; /* 对象的类型，包括：OBJ_STRING、OBJ_LIST、OBJ_HASH、OBJ_SET、OBJ_ZSET */
    unsigned encoding:4; /* 具体的数据结构 */
    unsigned lru:LRU_BITS; /* 24 位，对象最后一次被命令程序访问的时间，与内存回收有关 */
    int refcount; /* 引用计数。当 refcount 为 0 的时候，表示该对象已经不被任何对象引用，则可以进行垃圾回收了
    */
	void *ptr; /* 指向对象实际的数据结构 */
} robj;
```

可以使用 type 命令来查看对外的类型。

##### ③  内部编码

字符串类型的内部编码有三种： 

1、int，存储 8 个字节的长整型（long，2^63-1）。 

2、embstr, 代表 embstr 格式的 SDS（Simple Dynamic String 简单动态字符串）， 存储小于 44 个字节的字符串。 

3、raw，存储大于 44 个字节的字符串（3.2 版本之前是 39 字节）。为什么是39

```c++
/* object.c */
#define OBJ_ENCODING_EMBSTR_SIZE_LIMIT 44
```

##### 问题 1、什么是 SDS？ 

Redis 中字符串的实现。 在 3.2 以后的版本中，SDS 又有多种结构（sds.h）：sdshdr5、sdshdr8、sdshdr16、sdshdr32、sdshdr64，用于存储不同的长度的字符串，分别代表 2^5=32byte， 2^8=256byte，2^16=65536byte=64KB，2^32byte=4GB。

```c++
/* sds.h */
struct __attribute__ ((__packed__)) sdshdr8 {
    uint8_t len; /* 当前字符数组的长度 */
    uint8_t alloc; /*当前字符数组总共分配的内存大小 */
    unsigned char flags; /* 当前字符数组的属性、用来标识到底是 sdshdr8 还是 sdshdr16 等 */
    char buf[]; /* 字符串真正的值 */
}
```

##### 问题 2、为什么 Redis 要用 SDS 实现字符串？

我们知道，C 语言本身没有字符串类型（只能用字符数组 char[]实现）。 

- 1、使用字符数组必须先给目标变量分配足够的空间，否则可能会溢出。 
- 2、如果要获取字符长度，必须遍历字符数组，时间复杂度是 O(n)。 
- 3、C 字符串长度的变更会对字符数组做内存重分配。 
- 4、通过从字符串开始到结尾碰到的第一个'\0'来标记字符串的结束，因此不能保 存图片、音频、视频、压缩文件等二进制(bytes)保存的内容，二进制不安全。

SDS 的特点： 

- 1、不用担心内存溢出问题，如果需要会对 SDS 进行扩容。 
- 2、获取字符串长度时间复杂度为 O(1)，因为定义了 len 属性。 
- 3、通过“空间预分配”（ sdsMakeRoomFor）和“惰性空间释放”，防止多 次重分配内存。 
- 4、判断是否结束的标志是 len 属性（它同样以'\0'结尾是因为这样就可以使用 C语言中函数库操作字符串的函数了），可以包含'\0'。

| C字符串                                | SDS                                    |
| -------------------------------------- | -------------------------------------- |
| 获取字符串长度的复杂度为O（N）         | 获取字符串长度的复杂度为O（1）         |
| API是不安全的，可能造成缓冲区溢出      | API是安全的不会造成缓冲区溢出          |
| 修改字符串长度N次必然执行N次内存重分配 | 修改字符串N次最多需要执行N次内存重分配 |
| 只能保存文本数据                       | 可以保存文本或二进制数据               |
| 可以使用所有<string.h>库中的函数       | 可以使用一部分库中的函数               |

##### 问题 3、embstr 和 raw 的区别

​		embstr 的使用只分配一次内存空间（因为 RedisObject 和 SDS 是连续的），而 raw 需要分配两次内存空间（分别为 RedisObject 和 SDS 分配空间）。

 		因此与 raw 相比，embstr 的好处在于创建时少分配一次空间，删除时少释放一次 空间，以及对象的所有数据连在一起，寻找方便。
 	
 		而 embstr 的坏处也很明显，如果字符串的长度增加需要重新分配内存时，整个 RedisObject 和 SDS 都需要重新分配空间，因此 Redis 中的 embstr 实现为只读。

##### 问题 4：int 和 embstr 什么时候转化为 raw？

​		当 int 数 据 不 再 是 整 数 ， 或 大 小 超 过 了 long 的 范 围 （2^63-1=9223372036854775807）时，自动转化为 embstr。

##### 问题 5：明明没有超过阈值，为什么变成 raw 了

​		对于 embstr，由于其实现是只读的，因此在对 embstr 对象进行修改时，都会先 转化为 raw 再进行修改。 因此，只要是修改 embstr 对象，修改后的对象一定是 raw 的，无论是否达到了 44 个字节。

##### 问题 6：当长度小于阈值时，会还原吗？

​		关于 Redis 内部编码的转换，都符合以下规律：编码转换在 Redis 写入数据时完 成，且转换过程不可逆，只能从小内存编码向大内存编码转换（但是不包括重新 set）。

##### 问题 7：为什么要对底层的数据结构进行一层包装呢？

​		通过封装，可以根据对象的类型动态地选择存储结构和可以使用的命令，实现节省 空间和优化查询速度。

#### 2.  应用场景

##### ①  缓存

String 类型 例如：热点数据缓存（例如报表，明星出轨），对象缓存，全页缓存。 可以提升热点数据的访问速度。

##### ②  数据共享分布式

STRING 类型，因为 Redis 是分布式的独立服务，可以在多个应用之间共享 例如：分布式 Sessio

##### ③  分布式锁

STRING 类型 setnx 方法，只有不存在时才能添加成功，返回 true。 http://redisdoc.com/string/set.html 建议用参数的形式

```java
public Boolean getLock(Object lockObject){
	jedisUtil = getJedisConnetion();
	boolean flag = jedisUtil.setNX(lockObj, 1);
    if(flag){
        expire(locakObj,10);
    }
	return flag;
}
public void releaseLock(Object lockObject){
    del(lockObj);
}
```

##### ④  全局 ID

INT 类型，INCRBY，利用原子性

> 分库分表的场景，一次性拿一段

##### ⑤  计数器

INT 类型，INCR 方法 例如：文章的阅读量，微博点赞数，允许一定的延迟，先写入 Redis 再定时同步到 数据库。

#####  ⑥  限流

INT 类型，INCR 方法 以访问者的 IP 和其他信息作为 key，访问一次增加一次计数，超过次数则返回 false。

##### ⑦  位统计

String 类型的 BITCOUNT（1.6.6 的 bitmap 数据结构介绍）。 字符是以 8 位二进制存储的

```shell
set k1 a
setbit k1 6 1
setbit k1 7 0
get k1
```

a 对应的 ASCII 码是 97，转换为二进制数据是 01100001 

b 对应的 ASCII 码是 98，转换为二进制数据是 0110001

因为 bit 非常节省空间（1 MB=8388608 bit），可以用来做大数据量的统计。 例如：在线用户统计，留存用户统计

```shell
setbit onlineusers 0 1
setbit onlineusers 1 1
setbit onlineusers 2 0
```

支持按位与、按位或等等操作。

```shell
BITOP AND destkey key [key ...] ，对一个或多个 key 求逻辑并，并将结果保存到 destkey 。
BITOP OR destkey key [key ...] ，对一个或多个 key 求逻辑或，并将结果保存到 destkey 。
BITOP XOR destkey key [key ...] ，对一个或多个 key 求逻辑异或，并将结果保存到 destkey 。
BITOP NOT destkey key ，对给定 
```

 计算出 7 天都在线的用户

```shell
BITOP "AND" "7_days_both_online_users" "day_1_online_users" "day_2_online_users" ... "day_7_online_users"
```

如果一个对象的 value 有多个值的时候，怎么存储？ 例如用一个 key 存储一张表的数

序列化？例如 JSON/Protobuf/XML，会增加序列化和反序列化的开销，并且不能 单独获取、修改一个值。 可以通过 key 分层的方式来实现，例如：

```shell
mset student:1:sno GP16666 student:1:sname 沐风 student:1:company 腾讯
```

获取值的时候一次获取多个值：

```shell
mget student:1:sno student:1:sname 
```

缺点：key 太长，占用的空间太多。有没有更好的方式？

### Hash哈希

![image-20220904230223219](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220904230223219.png)

#### 存储类型

包含键值对的无序散列表。value 只能是字符串，不能嵌套其他类型。

> 同样是存储字符串，Hash 与 String 的主要区别？ 
>
> 1、把所有相关的值聚集到一个 key 中，节省内存空间
>
> 2、只使用一个 key，减少 key 冲突
>
> 3、当需要批量获取值的时候，只需要使用一个命令，减少内存/IO/CPU 的消耗

Hash 不适合的场景：

- 1、Field 不能单独设置过期时间 
- 2、没有 bit 操作 
- 3、需要考虑数据量分布的问题（value 值非常大的时候，无法分布到多个节点）

#### 存储（实现）原理

Redis 的 Hash 本身也是一个 KV 的结构，类似于 Java 中的 HashMap。

外层的哈希（Redis KV 的实现）只用到了 hashtable。当存储 hash 数据类型时， 我们把它叫做内层的哈希。内层的哈希底层可以使用两种数据结构实现： 

ziplist：OBJ_ENCODING_ZIPLIST（压缩列表） 

hashtable：OBJ_ENCODING_HT（哈希表）

#### ziplist 压缩列表

##### ziplist 压缩列表是什么？

/* ziplist.c 源码头部注释 */ The ziplist is a specially encoded dually linked list that is designed to be very memory efficient. It stores both strings and integer values, where integers are encoded as actual integers instead of a series of characters. It allows push and pop operations on either side of the list in O(1) time. However, because every operation requires a reallocation of the memory used by the ziplist, the actual complexity is related to the amount of memory 

ziplist 是一个经过特殊编码的双向链表，它不存储指向上一个链表节点和指向下一 个链表节点的指针，而是存储上一个节点长度和当前节点长度，通过牺牲部分读写性能， 来换取高效的内存空间利用率，是一种时间换空间的思想。只用在字段个数少，字段值 小的场景里面。

##### ziplist 的内部结构

ziplist.c 源码第 16 行的注释：

*`<zlbytes> <zltail> <zllen> <entry> <entry> ... <entry> <zlen>`

![image-20220904231723146](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220904231723146.png)

```c++
typedef struct zlentry {
    unsigned int prevrawlensize; /* 上一个链表节点占用的长度 */
    unsigned int prevrawlen; /* 存储上一个链表节点的长度数值所需要的字节数 */
    unsigned int lensize; /* 存储当前链表节点长度数值所需要的字节数 */
    unsigned int len; /* 当前链表节点占用的长度 */
    unsigned int headersize; /* 当前链表节点的头部大小（prevrawlensize + lensize），即非数据域的大小 */
    unsigned char encoding; /* 编码方式 */
    unsigned char *p; /* 压缩链表以字符串的形式保存，该指针指向当前节点起始位置 */
} zlentry
```

编码 encoding（ziplist.c 源码第 204 行）

 #define ZIP_STR_06B (0 << 6) //长度小于等于 63 字节 

#define ZIP_STR_14B (1 << 6) //长度小于等于 16383 字节 

#define ZIP_STR_32B (2 << 6) //长度小于等于 4294967295 字节

![image-20220904231812940](https://typora-imagebed.oss-cn-beijing.aliyuncs.com/img/image-20220904231812940.png)