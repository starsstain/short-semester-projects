#include <stdio.h>
#include <math.h>
#include <stdlib.h>


// 函数：将M进制数转换为10进制数
int baseM_to_decimal(int x, double M) {
int dec_value = 0;
int power = 0;

while (x != 0) {
int rem = x % 10;
dec_value += rem * (int)pow(M, power);
x = x / 10;
power++;
}

return dec_value;
}


// 函数：使用栈将M进制数转换为任意非M进制数
// 栈结构体定义
typedef struct Stack {
    int data[50];  // 栈数据数组
    int top;  // 栈顶指针
} Stack;

// 初始化栈
void initStack(Stack *s) {
    s->top = -1;
}

// 判断栈是否为空
int isEmpty(Stack *s) {
    return s->top == -1;
}

// 入栈操作
void push(Stack *s, int x) {
    s->data[++s->top] = x;
}

// 出栈操作
int pop(Stack *s) {
    if (isEmpty(s)) {
        printf("Error: Stack is empty.\n");
        exit(1);
    }
    return s->data[s->top--];
}

// 将任意进制数转换为指定进制数
int convert(int M, int x, int new_base) {
	int stack[32];
	int top = -1;
	int result = 0;
	int remainder;

    Stack s;  // 初始化栈
    initStack(&s);  // 初始化栈
    while (x > 0) {  // 将x不断除以进制数取余数入栈，直到x为0为止
         remainder = x % new_base;
        push(&s, remainder);  // 入栈操作
        x =x/ new_base;  // 更新x的值
    }
    result = pop(&s);  // 不断从栈中弹出数字直到栈为空，即为转换后的结果
	return pop(&s);
}



// 函数：使用数组将M进制数转换为任意非M进制数
int stack_conversion(int x, int M, int new_base) {
int stack[32];
int top = -1;
int result = 0;

while (x != 0) {
stack[++top] = x % new_base;
x = x / new_base;
}

while (top != -1) {
result = result * 10 + stack[top--];
}

return result;
}



int main() {
int x ,M;
int new_base;
printf("请输入未知数X：");
scanf("%d",&x);
printf("请输入未知数X的M进制：");
scanf("%d",&M);
// a、求出此数x的10进制值
printf("x的10进制值为：%d\n", baseM_to_decimal(x,M));
//b、实现对x向任意的一个非M进制的数的转换
x=baseM_to_decimal(x,M);
printf("请输入要转换为任意非M进制的进制数：");
scanf("%d",&new_base);
printf("使用栈将二进制数%d转换到%d进制的值为：%d\n", x,new_base,stack_conversion(x,M,new_base));
//printf("使用栈将二进制数%d转换到%d进制的值为：%d\n", x,new_base,convert(M, x, new_base));  // 将待转换数转换为目标进制数并输出结果);
printf("使用数组二进制数%d转换到%d进制的值为：%d\n", x,new_base,stack_conversion(x,M,new_base));
return 0;
}


int baseM_to_decimal(int x, double M);
int array_conversion(int x, int M, int new_base);
int stack_conversion(int x, int M, int new_base);
int convert(int M, int x, int new_base);