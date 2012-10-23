package org.sgx.jmencoder.mplayer;

import java.io.File;

import org.sgx.jmencoder.mplayer.executors.OptionsExecutor;
import org.sgx.jmencoder.mplayer.videoInfoDetector.MediaInfo;
import org.sgx.jmencoder.options.SubtitleOption;
import org.sgx.utils.Utils;
/**
 * this is the conversor command generator-responsible... 
 * defines a DUMB_VIDEO_OUTPUT_FORMATS_NAMES like vcd, dvd, divx, xvid, mp4, etc, 
 * and for each of them generates a mencoder / ffmpeg command (taken from mplayer docs, forums, irc, etc...
 * 
 * @author sg
 *
 */
public class CopyOfFormat {


	public static String MPEG = "mpeg";
	public static String AVI = "avi";
	
	public static final String copyCodec = "copy";
	public static final String MPEG_1 = "mpeg1";
	public static final String MPEG_2 = "mpeg2";
	public static final String MPEG_XVCD = "xvcd";
	public static final String MPEG_XSVCD = "xsvcd";
	public static final String MPEG_DVD = "dvd";
	
	public static final String PAL = "pal";
	public static final String NTSC = "ntsc";
	public static final String MPEG_4 = "mpeg_4";
	public static final String MP4 = "mp4";//dumb name
	
	//audio
	public static final String MP3 = "mp3";
	public static final String AAC= "aac";
	public static final String FLV= "flv";
	public static final String  WMV= "wmv";
	public static final String M4A= "m4a";
	public static final String WAV= "wav";
	public static final String[] AUDIO_OUTPUT_SUPPORTED_FORMATS = 
		{MP3, AAC, FLV, WMV, M4A, WAV, copyCodec};
	
	
	
	public static final String PAL_DVD="PAL DVD", NTSC_DVD="NTCS DVD", PAL_SVCD="PAL SVCD",  NTSC_SVCD="NTSC SVCD", 
		PAL_VCD="PAL VCD",  NTSC_VCD = "NTCS VCD";
	public static final String XVID = "XVID", FFMPEG_AVI = "ffmpeg avi", FFMPEG_MPEG = "ffmpeg mpeg";
	public static final String DIVX4DVDP = "DIVX for DVD players";
	
	public static final String DIVX = "DIVX";
	public static final String IPOD = "iPOD", PSP="PSP";
	public static final String[] DUMB_VIDEO_OUTPUT_FORMATS_NAMES = 
		{PAL_DVD, NTSC_DVD,/* PAL_SVCD, NTSC_SVCD,*/ PAL_VCD, NTSC_VCD, XVID, DIVX4DVDP, DIVX, FFMPEG_AVI, FFMPEG_MPEG, FLV, MP4, IPOD, PSP};
	public static String getExtensionFor(String format) {
		if(format.equals(PAL_DVD) || format.equals(NTSC_DVD) || format.equals(PAL_SVCD) ||
				format.equals(NTSC_SVCD) ||format.equals(PAL_VCD) ||format.equals(NTSC_VCD) ||format.equals(FFMPEG_MPEG)) 
				
			return "mpg";
		else if(format.equals(FFMPEG_AVI)||format.equals(XVID)||format.equals(DIVX4DVDP)||format.equals(DIVX))
			return "avi";
		else if(format.equals(FLV))
			return "flv";
		else if(format.equals(MP4) ||format.equals(PSP) ||format.equals(IPOD))
			return "mp4";
		else
			throw new RuntimeException("format not recognized : "+format);
	}
	
	public static String mencoderCommandFor(String in, String out, MediaInfo info, 
			String format, SubtitleOption op) throws OptionNotImplementedException {
		
		String subFragment = op==null?"":(" "+OptionsExecutor.getSubtitleMencoderFormatFor(op)+" ");
		
		if(format.equals(XVID)) {
			return "mplayer"+File.separator+"mencoder.exe -forceidx "+	
				subFragment+			
				Utils.quotePath(in)+	
				//" -ofps "+Double.parseDouble(info.get(MediaInfo.ID_VIDEO_FPS,"24"))+
				" -ovc xvid -xvidencopts bitrate="+
				Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1152000"))/1000+
				":autoaspect "+
				" -vf scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+",harddup"+
				" -oac mp3lame -lameopts fast:preset=standard:br="+
				Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))/1000+				
				" -o "+Utils.quotePath(out);
		}
		else if(format.equals(DIVX)) {
			return 
				"mplayer"+File.separator+"mencoder.exe  " +subFragment+	
				" -ffourcc DX50 " +
				" -vf scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+",harddup"+
				" -forceidx -af resample=44100:lavcresample=44100 -srate "+"44100 -ovc lavc " +
				" -lavcopts vcodec=mpeg4:vhq:vbitrate="+(Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1152000"))/1000)+
				":vpass=1"+
				":keyint=12 -oac mp3lame -lameopts cbr:br=128" +/*(Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))/1000)+*/
				" "+
				Utils.quotePath(in)+
				" -o nul"+
			"\n\n"+
				"mplayer"+File.separator+"mencoder.exe  " +subFragment+	
				" -ffourcc DX50 " +
				" -vf scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+",harddup"+
				" -forceidx -af resample=44100:lavcresample=44100 -srate "+"44100 -ovc lavc " +
				" -lavcopts vcodec=mpeg4:vhq:vbitrate="+(Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1152000"))/1000)+
				":vpass=2"+
				":keyint=12 -oac mp3lame -lameopts cbr:br=128" +/*(Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))/1000)+*/
				" "+
				Utils.quotePath(in)+
				" -o "+Utils.quotePath(out);
//			Utils.quotePath(in)+
					
		}
		else if(format.equals(DIVX4DVDP)) {
			return "mplayer"+File.separator+"mencoder.exe  " +subFragment+	
			
//			" -ffourcc DX50 " +
//			" -vf scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+",harddup"+
//			" -forceidx -af resample=44100:lavcresample=44100 -srate "+"44100 -ovc lavc " +
//			" -lavcopts vcodec=mpeg4:vhq:vbitrate="+(Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1152000"))/1000)+
//			":keyint=12 -oac mp3lame -lameopts cbr:br=128" +/*(Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))/1000)+*/
//			" "+
			
//			" -ffourcc DIV3 " +
			" -ffourcc DIV3 " +
			" -vf scale="+720+":"+576+",harddup"+
			" -forceidx  -srate 44100 -ovc lavc " +
			" -lavcopts vcodec=mpeg4"+/*":vbitrate="+(Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1152000"))/1000)+*/
			":keyint=250 -oac mp3lame -lameopts cbr:br=128" +/*(Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))/1000)+*/
			" "+
			
			Utils.quotePath(in)+
			" -o "+Utils.quotePath(out);
		}
		else if(format.equals(PAL_VCD)) {		    
			return "mplayer"+File.separator+"mencoder.exe " +
					subFragment+	
					"-oac lavc -ovc lavc -of mpeg -mpegopts format=xvcd -vf scale=352:288,harddup " +
					"-srate 44100 -af lavcresample=44100 " +
					"-lavcopts vcodec=mpeg1video:keyint=25:vrc_buf_size=327:vrc_minrate=1152:vbitrate=1152:vrc_maxrate=1152:acodec=mp2:abitrate=224:vstrict=0:trell:mbd=2 " +
					"-ofps 25 -o " + Utils.quotePath(out)+" "+Utils.quotePath(in);
		}
		else if(format.equals(NTSC_VCD)) {		    
			return "mplayer"+File.separator+"mencoder.exe " +
					subFragment+	
					"-oac lavc -ovc lavc -of mpeg -mpegopts format=xvcd -vf " +
					"scale=352:240,harddup -srate 44100 -af lavcresample=44100 -lavcopts " +
					"vcodec=mpeg1video:keyint=18:vrc_buf_size=327:vrc_minrate=1152:" +
					"vbitrate=1152:vrc_maxrate=1152:acodec=mp2:abitrate=224 -ofps 30000/1001 " +
					"-o " + Utils.quotePath(out)+" "+Utils.quotePath(in);
		}
		else if(format.equals(NTSC_DVD)) {
			return "mplayer"+File.separator+"mencoder.exe " +
					subFragment+	
					"-oac lavc -ovc lavc -of mpeg -mpegopts format=dvd:tsaf " +
					"-vf scale=720:480,harddup -srate 48000 -af lavcresample=48000 " +
					"-lavcopts vcodec=mpeg2video:vrc_buf_size=1835:vrc_maxrate=9800:vbitrate=5000:" +
					"keyint=18:vstrict=0:acodec=ac3:abitrate=192:aspect=16/9 -ofps 30000/1001 " +
					"-o " + Utils.quotePath(out)+" "+Utils.quotePath(in);
		}
		else if(format.equals(PAL_DVD)) {		    
			return "mplayer"+File.separator+"mencoder.exe " +
					subFragment+	
					"-oac lavc -ovc lavc -of mpeg -mpegopts format=dvd:tsaf " +
					"-vf scale=720:576,harddup -srate 48000 -af lavcresample=48000 " +
					"-lavcopts vcodec=mpeg2video:vrc_buf_size=1835:vrc_maxrate=9800:vbitrate=5000:" +
					"keyint=15:vstrict=0:acodec=ac3:abitrate=192:aspect=16/9 -ofps 25 " +
					"-o " + Utils.quotePath(out)+" "+Utils.quotePath(in);
		}
		  
		else if(format.equals(CopyOfFormat.FLV)) {					
//			Creating a Macromedia Flash video suitable for playback in a web browser with the Macromedia Flash plugin:
			return "mplayer"+File.separator+"mencoder.exe "+subFragment+
			Utils.quotePath(in)+ " -o " +
			Utils.quotePath(out)+ " -of lavf -oac mp3lame -lameopts abr:br=" +
				info.get(MediaInfo.ID_AUDIO_BITRATE,"56000")+
					" -srate 22050 -ovc lavc -lavcopts vcodec=flv:vbitrate=" +
					info.get(MediaInfo.ID_VIDEO_BITRATE,"56000")+":mbd=2:mv0:trell:v4mv:cbp:last_pred=3" +
					"";
		}
		else if(format.equals(CopyOfFormat.FFMPEG_AVI) || format.equals(CopyOfFormat.FFMPEG_MPEG)) {
			return "ffmpeg"+File.separator+"ffmpeg.exe -y -i "+Utils.quotePath(in)+" -ab "+Integer.parseInt(info.get(MediaInfo.ID_AUDIO_BITRATE,"128000"))+ 
			" -ar 22050 -b " +Integer.parseInt(info.get(MediaInfo.ID_VIDEO_BITRATE,"1024000"))+
			" -s "+info.get(MediaInfo.ID_VIDEO_WIDTH)+"x"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+ " "+Utils.quotePath(out)+"";
		}
		else if(format.equals(CopyOfFormat.MP4)) {	
			return "mplayer"+File.separator+"mencoder.exe "+subFragment+
				" -vf scale="+info.get(MediaInfo.ID_VIDEO_WIDTH)+":"+info.get(MediaInfo.ID_VIDEO_HEIGHT)+",harddup"+
				" -oac faac -faacopts mpeg=4:raw:br=128 -ovc lavc " +
				"-lavcopts vcodec=mpeg4:vbitrate=1200:mbd=2:cmp=2:subcmp=2:trell=yes:v4mv=yes:aic=2:vglobal=1:aglobal=1 " +
				"-ffourcc mp4v -of lavf -lavfopts format=mp4 " +
				"-o " + Utils.quotePath(out)+" "+Utils.quotePath(in); 
		}
		else if(format.equals(CopyOfFormat.IPOD)) { //from http://ffmpeg.mplayerhq.hu/faq.html#SEC25
			return "ffmpeg"+File.separator+"ffmpeg.exe " +"-i "+Utils.quotePath(in)+
				" -acodec libfaac -ab 128kb -vcodec mpeg4 -b 1200kb -mbd 2 -flags +4mv+trell -aic 2 -cmp 2 -subcmp 2 -s 320x180 -title X " +
				Utils.quotePath(out);
		}
		else if(format.equals(CopyOfFormat.PSP)) { //from http://ffmpeg.mplayerhq.hu/faq.html#SEC25
			return "ffmpeg"+File.separator+"ffmpeg.exe " +
				"-i "+Utils.quotePath(in)+" -acodec libfaac -ab 128kb -vcodec libx264 -b 1200kb -ar 48000 -mbd 2 -coder 1 -cmp 2 -subcmp 2 -s 368x192 -r 30000/1001 -title X -f psp -flags loop -trellis 2 -partitions parti4x4+parti8x8+partp4x4+partp8x8+partb8x8 " +
				Utils.quotePath(out);
		}
		else
			throw new OptionNotImplementedException("format not implemented yet");
	}
}
