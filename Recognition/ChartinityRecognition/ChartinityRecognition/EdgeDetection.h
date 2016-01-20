#pragma once
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <stdio.h>

using namespace cv;

class EdgeDetection
{
private:
	Mat src, src_gray;
	Mat dst, detected_edges;

	int edgeThresh = 1;
	int lowThreshold;
	int const max_lowThreshold = 200;
	int ratio = 1.2;
	int kernel_size = 3;
	char* window_name = "Edge Map";

public:
	EdgeDetection(Mat);
	~EdgeDetection();
	Mat CannyThreshold(int, void*);
};

