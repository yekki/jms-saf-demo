/**
 * WebLogic Automation Book Source Code (JMX sources)
 * 
 * This file is part of the WLS-Automation book sourcecode software distribution. 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Martin Heinzl
 * Copyright (C) 2013 MH-EnterpriseConsulting, All rights reserved.
 *
 */
package me.yekki.jmx.tests;

import me.yekki.jmx.monitoring.WTCMonitoring;
import me.yekki.jmx.utils.JMXWrapperRemote;

public class WTCTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		JMXWrapperRemote myJMXWrapperRemote = new JMXWrapperRemote();

		myJMXWrapperRemote.connectToAdminServer(false, false, "weblogic", "<password>", "t3://testhost.wlsautomation.de:7001");

    	
		WTCMonitoring myWTCMonitoring = new WTCMonitoring(myJMXWrapperRemote);
		
		myWTCMonitoring.monitorWTC();
	}
}
