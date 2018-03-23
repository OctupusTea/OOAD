#ifndef TRIANGLE
#define TRIANGLE

#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"

#include <algorithm>
#include <iostream>

using namespace std;

class Triangle : public Shape
{
	public:
		Triangle( const Point &_center, const double _length );

		void Render( void );

		static pair< Point, Size > CenterLength_To_CornerSize( const Point &_center, const double _length );
};

#endif
