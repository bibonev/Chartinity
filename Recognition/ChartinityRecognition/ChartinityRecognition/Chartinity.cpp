#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"

#include <stdlib.h>
#include <stdio.h>
#include <iostream>

#include "EdgeDetection.h"
#include "Chartinity.h"
#include "HoughLineTransform.h"
#include "CornerHarris.h"

using namespace cv;
using namespace std;

Chartinity::Chartinity()
{
}

Chartinity::~Chartinity()
{
}

int main(int argc, char** argv)
{
	Mat src = imread(argv[1]);

	if (!src.data)
	{
		cout << "The image cannot be loaded." << endl;
		return -1;
	}

	Mat result;

	/*Corner Detection*/
	CornerHarris ch(src);
	result = ch.CornerDetection(0, 0);
	imshow("Corner Detection", result);

	/*Hough Line Transform*/
	/*
	HoughLineTransform hlf(src);
	result = hlf.ProbabilisticLineTransform(0, 0);
	imshow("HLF", result);
	*/

	/*Canny Edge Detection*/
	/*
	EdgeDetection ed(src);
	result = ed.CannyThreshold(0, 0);
	imshow("Canny", result);
	*/

	waitKey(0);
	return 0;
}
