var gulp = require('gulp'),
    shell = require('gulp-shell'),
    inject = require('gulp-inject'),
    uglify = require('gulp-uglifyjs'),
    spawn = require('child_process').spawn,
    exec = require('child_process').exec,
    paths = {
        index: './app/views/index.scala.html',
        views: './app/views',
        components: ['./app/assets/**/*.js', './app/assets/**/*.css']
    };

gulp.task('default', ['index']);

gulp.task('shorthand', shell.task([
  'echo hello',
  'echo world'
]))

/**
 * loads all of the javascript and css files into
 * index.scala.html.
 */
gulp.task('index', function() {
    return gulp.src(paths.index)
        .pipe(inject(gulp.src(paths.components[0], {
            read: false
        }), {
            relative: true
        }))
        .pipe(inject(gulp.src(paths.components[1], {
            read: false
        }), {
            relative: true
        }))
        .pipe(gulp.dest(paths.views));
});

// gulp.task('debug', function() {
// 	var a = spawn('activator', ['run', '&']);
// 	console.log(a);
// });

// gulp.task('watch', function() {
//     gulp.watch(paths.components, ['index']);
// });

// gulp.task('uglify', function() {
//     gulp.src(paths.components[0])
//         .pipe(uglify('app.min.js', {}))
//         .pipe(gulp.dest('./public/javascripts'))
// });

// gulp.task('index-min', function() {
// 	return gulp.src(paths.index)
//         .pipe(inject(gulp.src('./public/javascripts/*.min.js', {
//             read: false
//         }), {
//             relative: true
//         }))
//         .pipe(inject(gulp.src(paths.components[1], {
//             read: false
//         }), {
//             relative: true
//         }))
//         .pipe(gulp.dest(paths.views));
// })
