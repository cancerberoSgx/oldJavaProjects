package org.sgx.jmencoder.mplayer.executors;

import java.io.File;

import org.sgx.jmencoder.options.SubtitleOption;
import org.sgx.utils.WindowsUtils;

/**
 * knows how to interpret mplayer options and generate mplayer commands 
 * @author sgurin
 *
 */
public class OptionsExecutor {

	public static String getSubtitleCommand(SubtitleOption o){
		File sub=o.getSubtitleFile();
		String subFont = o.getFont().getFontFamily().getFontFamily();
		return sub==null?"":" -sub \""+sub.getAbsolutePath()+"\" "+
				(subFont==null?"":(" -font \""+subFont+"\" "))+			
				" -slang es -subcp latin1 "+ //TODO: meter en subtitleoption			
//				" -sub-bg-alpha 75 " +//#background color ala closed captions
//				" -sub-bg-color 1 "+
				"";
	}

	public static String getSubtitleMencoderFormatFor(SubtitleOption subtitle) {
		File sub=subtitle.getSubtitleFile();
		String subDelaySegs=(Double.parseDouble(subtitle.getSubDelay())/1000.0)+"";
		String subFontScaleB=subtitle.getFont().getSubScale();
		String subFont = subtitle.getFont().getFontFamily().getFontFamily();
		return /*" -ovc copy -oac copy " +	*/		
			" -sub \""+sub.getAbsolutePath()+"\" "+
			" -subdelay "+subDelaySegs+ 
			" -subfont-text-scale "+subFontScaleB+
//			(subFont==null?"":(" -font \""+WindowsUtils.fontNameFile.get(subFont)+"\" "))+
			" -slang "+subtitle.getLang()+" -subcp "+subtitle.getCp()+" "+
			"";
	
	}

}
