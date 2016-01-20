#include "CornerHarris.h"


CornerHarris::CornerHarris(Mat srcInput)
{
	src = srcInput;
	cvtColor(src, src_gray, CV_BGR2GRAY);
	dst = Mat::zeros(src.size(), CV_32FC1);
}


CornerHarris::~CornerHarris()
{
	src.release();
}

Mat CornerHarris::CornerDetection(int, void*)
{
	/// Detector parameters
	int blockSize = 2;
	int apertureSize = 3;
	double k = 0.04;

	/// Detecting corners
	cornerHarris(src_gray, dst, blockSize, apertureSize, k, BORDER_DEFAULT);

	/// Normalizing
	normalize(dst, dst_norm, 0, 255, NORM_MINMAX, CV_32FC1, Mat());
	convertScaleAbs(dst_norm, dst_norm_scaled);

	/// Drawing a circle around corners
	for (int j = 0; j < dst_norm.rows; j++)
	{
		for (int i = 0; i < dst_norm.cols; i++)
		{
			if ((int)dst_norm.at<float>(j, i) > thresh)
			{
				circle(dst_norm_scaled, Point(i, j), 5, Scalar(0), 2, 8, 0);
			}
		}
	}

	return dst_norm_scaled;
}
