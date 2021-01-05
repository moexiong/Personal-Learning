### Linux的探索之路，一步一个坑
#### 2019.08.20
##### 问题描述：
> 在装了windows和linux双系统的情况下开机的时候出现unknown filesystem错误。
##### 原因分析：
> 一般是因为更改了windows下的磁盘分区（拆分或合并），再或者是windows系统自动升级等等，从而导致引导开机的grub找不到原先的linux所在分区，使得无法正确进入操作系统。
##### 解决方案：
> 首先先查看一下linux下的磁盘各个分区

`ls` 查看所有的磁盘分区
> 看到了所有的磁盘分区后，查看当前配置的环境变量

`set` 查看环境变量配置信息
> 会出现带有`root=hd0,msdos7`,`prefix=(hd0,msdos7)/boot/grub`这两行的信息，这两行需要进行调整，msdos7指的就是当前选择的磁盘分区，需要从上面看到的磁盘分区中选择出linux系统所在的分区，如果记得最好，不记得也没关系，如果不记得，只有一个个试了。

`set root=hd0,msdos6` 随便选择一个分区msdos6输入

`set prefix=(hd0,msdos6)/boot/grub` 选择的分区上下要求一样

> 查看当前配置的环境变量是否正确

`insmod normal` 检查配置是否正确

> 应该是不正确的，会出现unknown filesystem错误，原因同上，这个时候需要重新设置分区，继续上述步骤，当没有任何信息提示的时候，就表示所选分区正确了。

> 分区选择正确之后需要启用它

`normal` 正常启用
> 然后就自动重启，进入。

> **最重要的一步，更新grub的配置，否则下次问题还会回来的**

`sudo update-grub` 更新grub

`sudo grub-install /dev/sda` 更新配置

