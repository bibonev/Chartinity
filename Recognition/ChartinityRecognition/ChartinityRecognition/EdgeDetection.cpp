#include "EdgeDetection.h"

#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/highgui/highgui.hpp"
#include <stdlib.h>
#include <stdio.h>

using namespace cv;


EdgeDetection::EdgeDetection(Mat srcInput)
{
	src = srcInput;
}

EdgeDetection::~EdgeDetection()
{
	src.release();
}

Mat EdgeDetection::CannyThreshold(int, void*)
{
	dst.create(src.size(), src.type());

	cvtColor(src, src_gray, CV_BGR2GRAY);

	/// Reduce noise with a kernel 3x3
	blur(src_gray, detected_edges, Size(3, 3));

	/// Canny detector
	Canny(detected_edges, detected_edges, lowThreshold, lowThreshold*ratio, kernel_size);

	/// Using Canny's output as a mask, we display our result
	dst = Scalar::all(0);

	src.copyTo(dst, detected_edges);

	return dst;
}
