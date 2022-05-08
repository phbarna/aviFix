# aviFix
for converting all divx/xvid to mp4.  You need ffmpeg in your path.

Some new tvs don't play avi files which use the DIVX/XVID encoder.  

This program prompts you to enter a 'root' folder (i.e. where all your media files are).  Then you just leave it going (could take a long time if you have loads of files).

The files are recursively converted to .mp4.  

Note that the program needs a dependency - the ffmpeg .exe has to be installed and in your path. This would be a good reason to dockerize the program at some point.
