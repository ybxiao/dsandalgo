package com.example.dsandalgo.algo;


/*
电梯调度
题干：某个大厦有N部电梯，总共L层，需要实现调度逻辑如下。
当一个用户请求使用电梯时，会带有当前楼层startLevel，和目标楼层endLevel
电梯运行要求：
1. 对于用户请求，优先选择当前运行中且与用户同方向的的电梯，并且电梯现有行程起止区间必须包含用户起点，如有多个选择当前距离用户最近的。
2. 否则从闲置的电梯中选择一部当前距离用户最近的去接用户。
3. 电梯到达目的地后即停止。

注意：本系统是有状态的，状态请自行维护

代码需要实现以下两个接口。
A. int request(int startLevel, int endLevel)
用于响应用户请求。
返回值表示哪部电梯响应
B. void next()
用于模拟电梯移动。
表示每经过一个单位时间（电梯移动一层的时间定义为一个单位时间），电梯到达的新的状态


测试例：
输入依次调用
request(1, 3)
next()
预期电梯状态是0号电梯运行到2楼，其它电梯停在1楼
*/
public class T_ele {
}
