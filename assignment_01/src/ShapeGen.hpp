#ifndef SHAPEGEN
#define SHAPEGEN

#include "Vector.hpp"
#include "Point.hpp"
#include "Size.hpp"
#include "Shape.hpp"
#include "Circle.hpp"
#include "Square.hpp"
#include "Triangle.hpp"

#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

#define GEN_RAND_MAX 20
#define GEN_RAND_MIN 5
#define FLOAT_RAND_MAX 100.0
#define INT_RAND_MAX 100

enum shapeGenStat { GEN_UNDETERMINED = 0, GEN_INITIALIZED = 1, GEN_GENERATED = 2 };
enum shapeType { SHAPE_NONE = 0, SHAPE_CIRCLE = 1, SHAPE_SQUARE = 2, SHAPE_TRIANGLE = 3 };

class ShapeGen
{
	private:
		vector< Shape* > shape_list;
		int gen_count;
		int random_seed;
		shapeGenStat gen_stat;

		void Initialize( const int _gen_count );

		static shapeType Random_Shape( void );
		static Point Random_Point( void );
		static Size Random_Size( void );
		
		static double Random_Float( const double _min = 0.0, const double _max = FLOAT_RAND_MAX );
		static int Random_Interger( const int _min = 0, const int _max = INT_RAND_MAX );

	public:
		static const vector< Shape* > shape_list_void;
		static const Shape shape_void;

		ShapeGen( const int _gen_count = 0 );
		ShapeGen( const int _gen_count, const long _random_seed );
		
		const vector< Shape* >& Generate( void );
		
		void Randomize( const int _gen_count = 0 );
		void Randomize( const int _gen_count, const long _random_seed );

		const vector< Shape* >& Shape_List( void ) const;
		const Shape& Shape_List( const size_t index ) const;
};

#endif
