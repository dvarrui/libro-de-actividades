/* SDL's portable threading API */

#include <SDL/SDL.h>
#include <stdio.h>
#include <stdlib.h>
#include <SDL/SDL_thread.h>

static int counter = 0;
SDL_mutex *counter_mutex;

// The three threads will run until this flag is set
static int exit_flag = 0;

// This function is a thread entry point
int ThreadEntryPoint(void *data)
{
  char *threadname;

  // Anything can be passed as thread data. We will use it as a thread name
  threadname = (char *)data;

  // Loop until main() sets the exit_flag
  while (exit_flag == 0)
    {
      printf("This is %s!\n", threadname);

      // Get a lock on the counter variable
      SDL_mutexP(counter_mutex);

      // We can now safely modify the counter
      printf("The counter is currently %i\n", counter);
      counter++;

      // Release the lock on the counter variable
      SDL_mutexV(counter_mutex);

      // Delay for a random amount of time
      SDL_Delay(rand() % 3000);
    }

  printf("%s is now exiting\n", threadname);
  return 0;
}

int main()
{
  SDL_Thread *thread1, *thread2, *thread3;

  // Create a mutex to protect the counter
  counter_mutex = SDL_CreateMutex();

  printf("Ctrl-C to exit the program\n");

  // create 3 threads. Give each thread a name and its data
  thread1 = SDL_CreateThread(ThreadEntryPoint, "Thread 1");
  thread2 = SDL_CreateThread(ThreadEntryPoint, "Thread 2");
  thread3 = SDL_CreateThread(ThreadEntryPoint, "Thread 3");

  // Let the threads run until the counter hits 20
  while (counter < 20)
    SDL_Delay(1000);

  // Signal the threads to exit
  exit_flag = 1;
  printf("exit_flag has been set by main()\n");

  // Give them time to notice and exit
  SDL_Delay(3000);

  // Destroy the counter mutex
  SDL_DestroyMutex(counter_mutex);
}



