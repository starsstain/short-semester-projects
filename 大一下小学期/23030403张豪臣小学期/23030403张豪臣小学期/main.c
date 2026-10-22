#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<conio.h>

/*****声明仓库管理结构体*****/
typedef struct{
	char num[10]; //货物编号
	char name[20]; //货物名称
	char tele[12]; //货物数量
	char place[20]; //存放地点
	char peoplename[50]; //保管员姓名
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

/*****显示货物信息*****/
void Show(LinkList L){
	LinkList p=L;
system("cls");
printf("管理系统共有%d个货物\n\n",Add_Num);
printf("货物编号\t货物名称\t货物数量\t存放地点\t保管员姓名\n");
while(p->next){
p=p->next;
printf("%-8s%-8s%-8s%-8s%-15s\n",
p->data.num,p->data.name,p->data.tele,p->data.place,p->data.peoplename);
}
printf("\n");
printf("\n按任意键返回菜单!");
getch();
}

/*****新建仓库信息*****/
//尾插法创建单链表结构存储货物信息
void New_One(){
	int i;
	LinkList p,r;//分配头结点存储空间
	S=(LinkList)malloc(sizeof(LNode));
	if(S==NULL){//判断是否分配成功
		printf("内存空间分配失败!");
		exit(0);
	}
	S->next=NULL;
	r=S;//r是尾指针，指内最后一个结点
	printf("\n请输入新建货物的数目\n");
	scanf("%d",&Add_Num);//输入新建货物数
	for(i=0;i<Add_Num;i++){
		p=(LinkList)malloc(sizeof(LNode));//分配新结点存储空间
		if(p==NULL){
		printf("内存空间分配失败!");
		exit(0);
}
		system("cls");//清屏
		printf("请输入第%d个货物信息:\n",i+1);
		printf("请输入货物编号:");
		scanf("%s",p->data.num);
		printf("\n请输入货物名称:");
		scanf("%s",p->data.name);
		printf("\n请输入货物数量:");
		scanf("%s",p->data.tele);
		printf("\n请输入存放地点:");
		scanf("%s",p->data.place);
		printf("\n请输入保管员姓名:");
		scanf("%s",p->data.peoplename);
		p->next=NULL;//当前新结点是最后一个结点
		r->next=p;//将新结点插入到表尾
		r=p;//尾指针指向新结点
}
	printf("\n输入成功!");
	Show(S);//显示链表所有信息
}




/*****添加联系人*****/
//在单链表尾部添加1个联系人信息
void Append(LinkList L){
	int i=0;
	LinkList p,r; // 定义变量i和p，分别用于遍历链表和指向当前节点
	p=L;// 将当前节点赋值给p，开始遍历链表
	while(p&&i<Add_Num){ // 当p非空且遍历次数小于Add_Num时，继续遍历
		p=p->next;
		i++;
}
	r=(LinkList)malloc(sizeof(LNode));// 分配新的节点内存空间，赋值给r
	if(r==NULL){// 判断内存是否分配成功，如果内存分配失败，输出错误信息并退出程序
		printf("内存空间分配失败!");//未成功
		exit(0);
}
	system("cls"); // 清空屏幕，方便输入
	printf("请输入新的联系人信息:\n");//输出
	printf("请输入货物编号:");
	scanf("%s",r->data.num);//输入信息
	printf("\n请输入货物名称:");
	scanf("%s",r->data.name);
	printf("\n请输入货物数量:");
	scanf("%s",r->data.tele);
	printf("\n请输入存放地点:");
	scanf("%s",r->data.place);
	printf("\n请输入保管员姓名:");
	scanf("%s",r->data.peoplename);
	p->next=r;
	r->next=NULL;
	Add_Num++;// 将新的节点添加到链表尾部，并更新Add_Num计数器
	printf("\n输入成功!");
	Show(L);
}



/*****按货物编号编号查找信息*****/
void Find_Num(LinkList L){
	LinkList p;
	char number[10];
	system("cls");
	printf("请输入要查询的货物编号:\n");
	scanf("%s",number);
	p=L->next;
	while(p){
	if(strcmp(p->data.num,number)==0){
		printf("\n\n编号%s的数据如下:n",number);
		printf("货物编号\t货物名称\t货物数量\t存放地点\t保管员姓名\n");
		printf("%-8s%-8s%-8s%-8s%-15s\n",
	p->data.num,p->data.name,p->data.tele,p->data.place,p->data.peoplename);
		break;
	}
	p=p->next;
	}
	if(p==NULL)
	printf("\n该数据不存在!\n");
	printf("\n");
	printf("\n按任意键返回菜单!");
	getch();
}

/*****按货物名称查找信息*****/
void Find_Name(LinkList L){
	LinkList p;
	char name[20];
	system("cls");
	printf("请输入要查询的货物名称:\n");
	scanf("%s",name);
	p=L->next;
	while(p){
	if(strcmp(p->data.name,name)==0){
		printf("\n\n编号%s的数据如下:\n",name);
		printf("货物编号\t货物名称\t货物数量\t存放地点\t保管员姓名\n");
		printf("%-8s%-8s%-8s%-8s%-15s\n",
	p->data.num,p->data.name,p->data.tele,p->data.place,p->data.peoplename);
		break;
	}
	p=p->next;
	}
	if(p==NULL)
	printf("\n该数据不存在!\n");
	printf("\n");
	printf("\n按任意键返回菜单!");
	getch();
}

/*****按货物数量查找信息*****/
void Find_Tele(LinkList L){
	LinkList p;
	char tele[12];
	system("cls");
	printf("请输入要查询的货物数量:\n");
	scanf("%s",tele);
	p=L->next;
	while(p){
	if(strcmp(p->data.tele,tele)==0){
		printf("\n\n编号%s的数据如下:\n",tele);
		printf("货物编号\t货物名称\t货物数量\t存放地点\t保管员姓名\n");
		printf("%-8s%-8s%-8s%-8s%-15s\n",
	p->data.num,p->data.name,p->data.tele,p->data.place,p->data.peoplename);
		break;
	}
	p=p->next;
	}
	if(p==NULL)
	printf("\n该数据不存在!\n");
	printf("\n");
	printf("\n按任意键返回菜单!");
	getch();
}

/*****更新货物信息*****/
void Update(LinkList L){
	LinkList p;
	char names[20];
	system("cls");
	printf("请输入要修改的货物名称:\n");
	scanf("%s",names);
	p=L->next;// 指向下一个节点，开始遍历链表
	while(p){// 遍历链表，查找需要修改的节点
	if(strcmp(p->data.name,names)==0){// 如果找到与输入名称匹配的节点，则输出该节点的信息并跳出循环
	printf("\n\n姓名%s的数据如下:\n",names);
	printf("货物编号\t货物名称\t货物数量\t存放地点\t联系人姓名\n");
	printf("%-8s%-8s%-8s%-8s%-15s\n",
		p->data.num,p->data.name,p->data.tele,p->data.place,p->data.peoplename);
	break;
	}
	p=p->next;
	}
	if(p==NULL)// 如果未找到匹配的节点，则输出提示信息
printf("\n该数据不存在!\n");
else{
printf("\n请重新输入货物的信息\n");
printf("请输入货物编号:");
scanf("%s",p->data.num);
printf("\n请输入货物名称:");
scanf("%s",p->data.name);
printf("\n请输入货物数量:");
scanf("%s",p->data.tele);
printf("\n请输入存放地点:");
scanf("%s",p->data.place);
printf("\n请输入联系人姓名:");
scanf("%s",p->data.peoplename);
printf("\n输入成功!");
}
	printf("\n");
	printf("\n按任意键返回菜单!");
	getch();
}







/*****删除货物信息*****/
void Delete(LinkList L){
	LinkList p,s;// 定义两个指针p和s，用于查找要删除的数据
	int flag_tail=1;//初始flag_tail为1，表示还未找到要删除的数
	char number[10]; // 用于输入要删除的编号的字符串数组
	system("cls");printf("请输入要删除的编号:\n");
	scanf("%s",number);
	p=L; // 初始化p为链表头节点
	while(p->next){// 当链表中还有节点时，循环查找要删除的数据
		if(strcmp(p->next->data.num,number)==0){// 如果找到了要删除的数据，将s指向该节点，然后将p指向下一个节点，释放s指向的节点内存，减少库存数量，并设置flag_tail为0，结束查找
			s=p->next;
			p->next=s->next;
			free(s);
			Add_Num--;
			flag_tail=0;
			break;
		}
	p=p->next;// 将p指向下一个节点
	}
		if(flag_tail){// 如果flag_tail为1，表示未找到要删除的数据，输出提示信息并等待用户按任意键退出菜单
			printf("\n该数据不存在，无法删除!\n");printf("\n");
			printf("\n按任意键返回菜单!");
			getch();
	}
		else{
			printf("\n删除成功!\n");
			printf("\n");
			printf("\n按任意键返回菜单!");
			getch();
	}
}

/*****排序函数*****/
//按货物名称从小到大排序
void Sort(LinkList L){
	int i,j;
	LinkList p;
	Address tmp;
	for(i=0;i<Add_Num-1;i++){
		p=L->next;
		for(j=0;j<Add_Num-i-1;j++){
			if(strcmp(p->data.name,p->next->data.name)>0){
				tmp=p->data;
				p->data=p->next->data;
				p->next->data=tmp;
			}
			p=p->next;
		}
	}
	Show(L);
}







/*****打开文件*****/
void Open(){
	char filename[30];
	FILE *fp;
	LinkList p,r;
	int i;
	S=(LinkList)malloc(sizeof(LNode));
	if(S==NULL){
		printf("内存空间分配失败!");
		exit(0);
	}
	S->next=NULL;
	r=S;
	printf("请输入要打开的文件名:\n");
	scanf("%s",filename);
	if((fp=fopen(filename,"rb+"))==NULL){
		printf("文件不能打开，按任意键退出\n");
		exit(0);
	}
	fread(&Add_Num,sizeof(int),1,fp);
	for(i=0;i<Add_Num;i++){
	p=(LinkList)malloc(sizeof(LNode));
	if(p==NULL){
		printf("内存空间分配失败!");
		exit(0);
	}
	fread(&(p->data),sizeof(Address),1,fp);
	p->next=NULL;
	r->next=p;
	r=p;
	}
	fclose(fp);
	printf("\n文件信息导入成功!\n");
	printf("\n按任意键返回菜单!");
	getch();
}

/*****保存文件*****/
void Save(){
	LinkList r;
	char filename[30];
	FILE *fp;//文件指针
	printf("请输入要保存的文件名\n");
	scanf("%s",filename);
	if((fp=fopen(filename,"wb"))==NULL){ //用”写”的方式打开文件
		printf("文件创建失败，按任意键退出\n");
		exit(0);
}
	fwrite(&Add_Num,sizeof(int),1,fp);
	r=S->next;
	while(r!=NULL){
		fwrite(&(r->data),sizeof(Address),1,fp);
		r=r->next;
	}
	fclose(fp);
	printf("\n文件保存成功!\n");
	printf("\n按任意键返回菜单!");
	getch();
}

/*****查询菜单函数*****/
int Menu_Find(){
	int choose;
	system("cls");
	printf("\n\n**************查找菜单**************\n");
	printf("\t 1.按货物编号查找\n");
	printf("\t 2.按货物名称查找\n");
	printf("\t 3.按货物数量查找\n");
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
	printf("\n\n       欢迎使用仓库管理系统\n\n");
	printf("**************主菜单**************\n");
	printf("\t 0.新建仓库信息\n");
	printf("\t 1.显示仓库信息\n");
	printf("\t 2.添加货物\n");
	printf("\t 3.查找货物\n");
	printf("\t 4.更新货物\n");
	printf("\t 5.删除货物\n");
	printf("\t 6.货物排序\n");
	printf("\t 7.打开仓库管理文件\n");
	printf("\t 8.保存仓库管理文件\n");
	printf("\t 9.退出仓库管理系统\n");
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
			case 6:Sort(S);break;
			case 7:Open();break;
			case 8:Save();break;
			case 9:exit(0);break;
			default:printf("输入错误，请重新输入!\n");
		}
	}
}