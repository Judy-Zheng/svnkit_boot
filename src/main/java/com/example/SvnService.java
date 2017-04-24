/**
 * Copyright 2015-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;

import java.io.ByteArrayInputStream;

/**
 * Purpose.
 *
 * A description of why this class exists.  
 *   For what reason was it written?  
 *   Which jobs does it perform?
 * {@code DataAccessException} using 
 * @author how
 * @date 17/4/14
 */
public class SvnService {

    public void commit() throws SVNException {
        DAVRepositoryFactory.setup();
        SVNURL svnurl = SVNURL.parseURIEncoded("https://182.92.186.10/svn/mszl/doc/00-项目管理/");
        SVNRepository svnRepository = DAVRepositoryFactory.create(svnurl);
        BasicAuthenticationManager basicAuthenticationManager = BasicAuthenticationManager.newInstance("zhanghw", "zhanghw123".toCharArray());
        svnRepository.setAuthenticationManager(basicAuthenticationManager);
        String logMessage = "log message";
        ISVNEditor editor = svnRepository.getCommitEditor(logMessage, null);
        try {
            byte[] contents = "This is a new file auto generate by machine.".getBytes( );
            SVNCommitInfo commitInfo = addDir( editor , "test" , "test/"+System.currentTimeMillis()+".txt" , contents );
            System.out.println( "The directory was added: " + commitInfo );
        } catch ( SVNException svne ) {
            editor.abortEdit( );
            throw svne;
        }
    }

    public static void main(String[] args) throws SVNException {
            new SvnService().commit();
    }
    private static SVNCommitInfo addDir(ISVNEditor editor , String dirPath , String filePath , byte[] data ) throws SVNException {
        editor.openRoot( -1 );
        //editor.addDir( dirPath , null , -1 );
        editor.openDir(dirPath,-1);
        editor.addFile( filePath , null , -1 );

        editor.applyTextDelta( filePath , null );

        SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator( );
        String checksum = deltaGenerator.sendDelta( filePath , new ByteArrayInputStream( data ) , editor , true );

        editor.closeFile(filePath, checksum);

        //Closes dirPath.
        editor.closeDir();

        //Closes the root directory.
        editor.closeDir();

        return editor.closeEdit();
    }
}
