function ViewImage(ifile,ix,iy,ititle) {
                var win;
                var sWidth;
                var sHeight;
                var NS = (document.layers) ? 1 : 0;
                win = window.open("","imageviewer","width="+ix+",height="+iy+",menubar=no,toolbar=no");
                if (NS) {
                    sWidth = win.innerWidth;
                    sHeight = win.innerHeight;
                } else {
                    sWidth = win.document.body.clientWidth;
                    sHeight = win.document.body.clientHeight;
                }
                if(sWidth!=ix || sHeight!=iy) {
                    win.close();
                    setTimeout("ViewImage('"+ifile+"',"+ix+","+iy+",'"+ititle+"')", 250);
                    return;
                }
                win.document.open();
                win.document.write("<html><head><title>"+ititle+"</title>");
                win.document.write("</head><body>");
                win.document.write('<div style="position:absolute;width:'+ix+'px;height:'+iy+'px;left:0px;top:0px">');
                win.document.write("<img src="+ifile+"></div></body></html>");
                win.document.close();
            }
