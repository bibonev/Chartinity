#pragma once
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <stdio.h>

using namespace cv;

class CornerHarris
{
private:
	Mat src, src_gray;
	
	int thresh = 200;
	int max_thresh = 255;

	Mat dst, dst_norm, dst_norm_scaled;
public:
	CornerHarris(Mat);
	~CornerHarris();
	Mat CornerDetection(int, void*);
};

