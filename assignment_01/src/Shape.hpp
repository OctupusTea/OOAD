#ifndef SHAPE
#define SHAPE

#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"

#include <algorithm>
#include <iostream>

using namespace std;

class Shape
{
	protected:
		Point corner;
		Size size;

	public:
		Shape( void );
		Shape( const Point &_corner, const Size &_size );

		const Point& Corner( void );
		const Size& Shape_Size( void );

		virtual void Render( void );

		bool operator<( const Shape &that ) const;

		static bool Compare( const Shape *a, const Shape *b );
};

#endif
