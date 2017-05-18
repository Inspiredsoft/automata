/*******************************************************************************
* Inspired Automata is a framework implement a Finite State machine.
* Copyright (C) 2017 Inspired Soft
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.    
*******************************************************************************/

package it.inspired.automata.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * I/O Utility 
 * @author Massimo Romano
 *
 */
public class FileUtils {
	
	public static Collection<File> getFiles(File path, String suffix) {
		Collection<File> result = new ArrayList<File>();
		if( path.isDirectory() ) {
			String[] filenames = path.list();
			for( int i = 0; i < filenames.length; i++ ) {
				File file = new File( path.getAbsolutePath() + File.separator + filenames[ i ] );
	
				if( file.getName().endsWith( suffix ) ) {
					result.add( file );
				} else if( file.isDirectory() ) {
					result.addAll( getFiles( file, suffix ) );
				}
			}
		} else {
			result.add( path );
		}
		return result;
	}
}
