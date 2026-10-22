#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<conio.h>

/*****声明通讯录结构体*****/
typedef struct{
	char num[10]; //编号
	char name[20]; //姓名
	char tele[12]; //电话
	char qq[20]; //qq
	char email[50]; //email
}Address;


/*****声明单链表结构*****/
typedef struct Node{
	Address data;
	struct Node *next;
}LNode;

/*****声明单链表指针*****/
typedef LNode *LinkList;

/*****定义单链表S*****/
LinkList S;

int Add_Num; //单链表的数据个数，即联系人的个数。

/*****显示通讯录信息*****/
void Show(LinkList L){
	
}


/*****新建通讯录*****/
//尾插法创建单链表结构存储联系人信息
void New_One(){
	
}


/*****添加联系人*****/
//在单链表尾部添加1个联系人信息
void Append(LinkList L){
	
}


/*****按编号查找信息*****/
void Find_Num(LinkList L){
	
}

/*****按姓名查找信息*****/
void Find_Name(LinkList L){

}

/*****按电话查找信息*****/
void Find_Tele(LinkList L){

}

/*****更新联系人信息*****/
void Update(){

}

/*****删除联系人信息*****/
void Delete(LinkList L){
	
}

/*****排序函数*****/
void Sort(){

}

/*****打开文件*****/
void Open(){
	
}

/*****保存文件*****/
void Save(){

}

/*****查询菜单函数*****/
int Menu_Find(){
	int choose;
	system("cls");
	printf("\n\n**************查找菜单**************\n");
	printf("\t 1.按编号查找\n");
	printf("\t 2.按姓名查找\n");
	printf("\t 3.按电话查找\n");
	printf("\t 4.返回主菜单\n");
	printf("**********************************\n");
	printf("\n请输入选项：\n");
	scanf("%d",&choose);
	return choose;	//返回用户输入的选项	
}



/*****主菜单函数*****/
int Menu_Main(){
	int choose;
	system("cls");
	printf("\n\n       欢迎使用通讯录管理系统\n\n");
	printf("**************主菜单**************\n");
	printf("\t 0.新建通讯录\n");
	printf("\t 1.显示通讯录\n");
	printf("\t 2.添加联系人\n");
	printf("\t 3.查找联系人\n");
	printf("\t 4.更新联系人\n");
	printf("\t 5.删除联系人\n");
	printf("\t 6.通讯录排序\n");
	printf("\t 7.打开通讯录文件\n");
	printf("\t 8.保存通讯录文件\n");
	printf("\t 9.退出通讯录系统\n");
	printf("**********************************\n");
	printf("\n请输入选项：\n");
	scanf("%d",&choose);
	return choose;	//返回用户输入的选项	
}

/*****主函数*****/
int main(){	
	int choose,choose_find;
	int flag;
	while(1){
		flag=1;
		choose=Menu_Main();
		switch(choose){
			case 0:New_One();break;
			case 1:Show(S);break;
			case 2:Append(S);break;
			case 3:
				while(flag){
					choose_find=Menu_Find();
					switch(choose_find){
					case 1: Find_Num(S);break;
					case 2: Find_Name(S);break;
					case 3: Find_Tele(S);break;
					case 4:flag=0;break;
					default:printf("输入错误，请重新输入!\n");
					}
				}break;
			case 4:Update(S);break;
			case 5:Delete(S);break;
			case 6:Sort();break;
			case 7:Open();break;
			case 8:Save();break;
			case 9:exit(0);break;
			default:printf("输入错误，请重新输入!\n");
		}
	}
}
