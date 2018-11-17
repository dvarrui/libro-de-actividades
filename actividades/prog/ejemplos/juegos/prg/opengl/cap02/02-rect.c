#include "GL/gl.h"
#include "GL/glu.h"
#include "GL/freeglut.h"

void RenderScene(void) {
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.0f, 0.0f, 1.0f);
	glRectf(-25.0f, 25.0f, 25.0f, -25.0f);
	glFlush();
}

void SetupRC(void) {
	glClearColor(0.0f, 0.0f, 1.0f, 1.0f);
}

void ChangeSize(GLsizei w, GLsizei h) {
	GLfloat aspectRatio;
	if (h==0) h=1;
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	aspectRatio = (GLfloat)w/(GLfloat)h;
	if (w<=h)
		glOrtho(-100.0,100.0,-100.0/aspectRatio, 100.0/aspectRatio, 1.0, -1.0);
	else
		glOrtho(-100*aspectRatio, 100.0*aspectRatio, -100.0, 100.0, 1.0, -1.0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int main(int argc, char *argv[]) {
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutCreateWindow("Rect");
	glutDisplayFunc(RenderScene);
	glutReshapeFunc(ChangeSize);
	
	SetupRC();
	
	glutMainLoop();
	return 0;
}
