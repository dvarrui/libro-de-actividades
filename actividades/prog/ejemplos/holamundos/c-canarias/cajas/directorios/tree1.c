/*
 * Página 126
 */

#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <string.h>

#define EQ(str1,str2) (strcmp((str1),(str2))==0)

struct opciones {
	unsigned mostrar_ficheros:1;
};

enum boolean {NO,SI};

int main(int argc, char *argv[])
{
	struct opciones opciones = {NO};
	int i, j;

	if (argc<2) {
		fprintf(stderr,"Forma de uso: %s [-f] directorio\n", argv[0]);
		return -1;
	}

	for(i=1;i<argc;i++)
		if(argv[i][0]=='-')
			for(j=1;argv[i][j]!='\0';j++)
				switch(argv[i][j]) {
				case 'f':
					opciones.mostrar_ficheros=SI;
					break;
				default:
					fprintf(stderr,"Opción [-%c] desconocida\n",argv[i][j]);
					return -1;
				}
	for(i=1;i<argc;i++)
		if(argv[i][0]!='-')
			tree(argv[i],opciones);
}

int tree(char *path, struct opciones opciones)
{
	DIR *dirp;
	struct dirent *dp;
	static nivel = 0;
	struct stat buf;
	int ok, i;
	char fichero[256], tipo_fichero;

	if((dirp=opendir(path))==NULL) {
		perror(path);
		return -1;
	}

	while((dp=readdir(dirp))!=NULL) {
		if (EQ(dp->d_name,".")||EQ(dp->d_name,".."))
			continue;

		sprintf(fichero,"%s/%s",path,dp->d_name);
		ok = stat(fichero,&buf);

		if (ok!=-1 && (buf.st_mode&S_IFMT)==S_IFDIR) {
			for(i=0;i<nivel;i++)
				printf("\t");
			printf("%s %s\n", dp->d_name, opciones.mostrar_ficheros ? "(d)": "");
			++nivel;
			tree(fichero,opciones);
			--nivel;
		} else if (ok!=-1 && opciones.mostrar_ficheros==SI) {
			for(i=0;i<=nivel; i++)
				printf("\t");
		
			switch(buf.st_mode&S_IFMT) {
			case S_IFREG:
				if(buf.st_mode & 0111)
					tipo_fichero='x';
				else
					tipo_fichero='o';
				break;
			case S_IFCHR:
				tipo_fichero='c';
				break;
			case S_IFBLK:
				tipo_fichero='b';
				break;
			case S_IFIFO:
				tipo_fichero='p';
				break;
			default:
				tipo_fichero='?';
			}
			printf("(%c) %s\n",tipo_fichero,dp->d_name);
		}
	}	
	closedir(dirp);
	return 0;
}

