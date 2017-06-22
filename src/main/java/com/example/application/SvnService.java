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
package com.example.application;

import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;

import java.io.ByteArrayInputStream;

/**
 * SVN 服务
 * 1) 提交
 * @author how
 * @date 17/4/14
 */
@Service
public class SvnService {

    public void commit(String wordStr,String datePath,String submitter) throws SVNException {
        DAVRepositoryFactory.setup();
        SVNURL svnurl = SVNURL.parseURIEncoded("https://182.92.186.10/svn/mszl/doc/00-项目管理/周报/");
        SVNRepository svnRepository = DAVRepositoryFactory.create(svnurl);

        BasicAuthenticationManager basicAuthenticationManager = BasicAuthenticationManager.newInstance("zhanghw", "zhanghw123".toCharArray());
        svnRepository.setAuthenticationManager(basicAuthenticationManager);

        SVNNodeKind nodeKind = svnRepository.checkPath(datePath, -1);
        String fileName = "个人周报-"+submitter+".doc";
        boolean needCreateDirectory = true;
        boolean fileAlreadyExist = false;
        if( nodeKind == SVNNodeKind.NONE ) {
            needCreateDirectory = true;
        }else if(nodeKind == SVNNodeKind.DIR){
            needCreateDirectory = false;
            SVNDirEntry dirEntry = svnRepository.info(datePath+fileName, -1);
            if(dirEntry !=null && (dirEntry.getKind() == SVNNodeKind.FILE)){
                fileAlreadyExist = true;
            }
        }
        String logMessage = "提交周报 message auto create by machine";
        ISVNEditor editor = svnRepository.getCommitEditor(logMessage, null);
        try {
            byte[] contents = wordStr.getBytes( );

            SVNCommitInfo commitInfo = addDir( editor , datePath , fileName , contents,needCreateDirectory,fileAlreadyExist);
            System.out.println( "The directory was added: " + commitInfo );
        } catch ( SVNException svne ) {
            editor.abortEdit( );
            throw svne;
        }
    }

    private static SVNCommitInfo addDir(ISVNEditor editor , String dirPath , String filePath , byte[] data ,boolean needCreateDirectory,boolean fileAlreadyExist) throws SVNException {
        editor.openRoot( -1 );
        if(needCreateDirectory){
            editor.addDir( dirPath , null , -1 );
        }else{
            editor.openDir(dirPath,-1);
        }
        if(fileAlreadyExist){
            editor.openFile(filePath,-1);
        }else{
            editor.addFile( filePath , null , -1 );
        }

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
