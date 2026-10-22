#include<stdio.h>
#include<string.h>
#include<process.h>
#define N 80
//********************定义影院管理系统**********************
typedef  struct cinema
{
char   num[4];
char   name[15];
char   place[20];
char   tele[15];
char   number[30];
char   people[10];
} Cinema;

typedef struct{
   Cinema ctu[N];
   int length;
} Seqlist;
Seqlist C;
char filename[30];

void dengji();
void xianshi();
void shanchu();
void chazhao();
void xiugai();
void tianjia();
void dakai();
void baocun();
void paixu();
void insert(int loc);
void insert2(int loc);

void main()
{ int m;
  while(1)
  {
	  printf("******欢迎使用影院管理系统******\n");
	  printf("*************主菜单**************\n");
	  printf("\t    0.登记管理系统\n");
	  printf("\t    1.显示管理系统\n");
	  printf("\t    2.删除管理系统\n");
	  printf("\t    3.查找管理系统\n");
	  printf("\t    4.修改管理系统\n");
	  printf("\t    5.添加管理系统\n");
	  printf("\t    6.打开管理系统\n");
	  printf("\t    7.保存管理系统\n");
	  printf("\t    8.按影院名称或放映厅数量排序管理系统\n");
	  printf("\t    9.退出管理系统\n");
	  printf("\t   请输入选项0-9\n");
	  scanf("%d",&m);
	  switch(m)
	  {
	  case 0:dengji();break;
	  case 1:xianshi();break;
	  case 2:shanchu();break;
	  case 3:chazhao();break;
	  case 4:xiugai();break;
	  case 5:tianjia();break;
	  case 6:dakai();break;
	  case 7:baocun();break; 
	  case 8:paixu();break;
	  case 9:exit(0);break;
	  default:printf("输入错误,请重新输入\n");
	}
  }
}


void dengji()
{
	int i,n;
    FILE *fp;
   system("cls");

   printf("\n请输入要加入通讯录的数目  \n");
   scanf("%d",&n);
   C.length=n;
   printf("请输入信息 \n");
   for(i=0;i<n;i++)
   {
	   printf("请输入编号：");
       scanf("%s",C.ctu[i].num);
	   printf("请输入影院名称：");
       scanf("%s",C.ctu[i].name);
	   printf("请输入地点：");
       scanf("%s",C.ctu[i].place);
	   printf("请输入电话：");
	   scanf("%s",C.ctu[i].tele);
	   printf("请输入放映厅数量：");
       scanf("%s",C.ctu[i].number);
	   printf("请输入联系人：");
       scanf("%s",C.ctu[i].people);
   }
   printf("管理系统信息已成功输入\n");
   printf("请输入要保存的文件名\n");
   scanf("%s",filename);

   if((fp=fopen(filename,"wb"))==NULL)
   {
	   printf("不能创建文件，按任意键退出");
	   exit(0);
   }
   int len=C.length;
   if(fwrite(&C,sizeof(Seqlist),1,fp)!=1)
   {  printf("文件创建失败！");
   }
   fclose(fp);
}

void xianshi()
{
	/*system("cls");
	int i;
	printf("编号\t影院名称\t地点电话\t放映厅数量\t联系人\n");
	for(i=0;i<=C.length-1;i++)
	{
	printf("%s\t %s\t %s\t %s\t %s\n",C.ctu[i].num,C.ctu[i].name,C.ctu[i].place,C.ctu[i].tele,C.ctu[i].number,C.ctu[i].people);
	}*/
}

void shanchu()
{
}

void chazhao()
{
}

void xiugai()
{
}

void tianjia()
{
}

void dakai()
{
	/*printf("请输入要打开的文件名\n");
	scanf("%s",filename);
	FILE *fp;
   if((fp=fopen(filename,"rb+"))==NULL)
   {
	   printf("不能打开文件，按任意键退出");
	   exit(0);
   }
   
   if(fread(&S,sizeof(Seqlist),1,fp)!=1)
   {  printf("文件读失败！");
   }

   fclose(fp);*/

}

void baocun()
{
	//printf("请输入要保存的文件名\n");
	//scanf("%s",filename);
	//FILE *fp;
 //  if((fp=fopen(filename,"wb"))==NULL)
 //  {
	//   printf("不能创建文件，按任意键退出");
	//   exit(0);
 //  }
 //  int len=C.length;
 //  if(fwrite(&S,sizeof(Seqlist),1,fp)!=1)
 //  {  printf("文件创建失败！");
 //  }
 //  fclose(fp);
}

void paixu()
{
}