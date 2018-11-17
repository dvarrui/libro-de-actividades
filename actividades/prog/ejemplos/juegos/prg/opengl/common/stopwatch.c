// Stopwatch.c
// Implements a stopwatch

#include "gltools.h"


// Reset the stopwatch
// You must call this at least once to set the stopwatch
void gltStopwatchReset(GLTStopwatch *pWatch)
    {
    #ifdef WIN32
    QueryPerformanceCounter(&pWatch->m_LastCount);
    #else
    gettimeofday(&pWatch->last, 0);
    #endif
    }


/////////////////////////////////////////////////////////////////////
// Get the number of seconds since the last reset
float gltStopwatchRead(GLTStopwatch *pWatch)
    {
    #ifdef WIN32
    LARGE_INTEGER m_CounterFrequency;
	LARGE_INTEGER lCurrent;

    QueryPerformanceFrequency(&m_CounterFrequency);
	QueryPerformanceCounter(&lCurrent);

	return (float)((lCurrent.QuadPart - pWatch->m_LastCount.QuadPart) /
										(float)(m_CounterFrequency.QuadPart));    
    #else
    struct timeval lcurrent;    // Current timer
    float  fSeconds, fFraction; // Number of seconds & fraction (microseconds)

    // Get current time
    gettimeofday(&lcurrent, 0);
        
    // Subtract the last time from the current time. This is tricky because
    // we have seconds and microseconds. Both must be subtracted and added 
    // together to get the final difference.
    fSeconds = (float)(lcurrent.tv_sec - pWatch->last.tv_sec);
    fFraction = (float)(lcurrent.tv_usec - pWatch->last.tv_usec) * 0.000001f;
    return fSeconds + fFraction;
    #endif
    }
