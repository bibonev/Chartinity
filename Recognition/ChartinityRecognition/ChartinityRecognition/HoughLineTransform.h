#pragma once
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"

#include <iostream>

using namespace cv;
using namespace std;

class HoughLineTransform
{
private:
	Mat src;
	Mat dst, cdst;
public:
	HoughLineTransform(Mat);
	~HoughLineTransform();
	Mat StandardHoughLineTransform(int, void*);
	Mat ProbabilisticLineTransform(int, void*);
};

