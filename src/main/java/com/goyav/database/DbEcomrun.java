package com.goyav.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goyav.model.configuration.X3v12prod;
import com.goyav.model.sqlserver.Tstk;
import com.goyav.model.sqlserver.Stock;
import com.goyav.model.sqlserver.TstkX3;
import com.goyav.model.sqlserver.Transfert;
import com.goyav.model.sqlserver.QueryTransfert;
import com.goyav.model.sqlserver.ResponseStock;
import com.goyav.model.sqlserver.Tappent;
import com.goyav.model.sqlserver.Tapplig;
import com.goyav.model.sqlserver.Tnumbl;
import com.goyav.model.sqlserver.Article;
import com.goyav.model.sqlserver.ArticleTarif;
import com.goyav.model.sqlserver.Rdepot;
import com.goyav.model.sqlserver.ResponseTransfert;
import com.goyav.model.sqlserver.TransactionForAppro;
import com.goyav.model.sqlserver.Appro;
import com.goyav.model.sqlserver.Target;
import com.goyav.model.sqlserver.ResponseTarif;
import com.goyav.model.sqlserver.Tarif;
import com.goyav.model.sqlserver.QueryTarif;
import com.goyav.utils.Tools;
import com.goyav.model.sqlserver.QueryStock;

public class DbEcomrun {
	private X3v12prod params;
	private Tools tools = new Tools();
	public DbEcomrun() {
		super();
		// TODO Auto-generated constructor stub
	}

	public X3v12prod getParams() {
		return params;
	}
	public DbEcomrun(X3v12prod params) {
		super();
		this.params = params;
	}

	public void setParams(X3v12prod params) {
		this.params = params;
	}

	public List<TstkX3> queryStockEcomrunExtended (Connection conn) throws SQLException {
		ArrayList<TstkX3> stocks = new ArrayList<TstkX3>();
		

		String SQL = "SELECT * \r\n" + 
				"FROM \r\n" + 
				"(SELECT REQ1.SITE, REQ1.CODERAV, REQ1.CODEX3,REQ1.STKDISPO + \r\n" + 
				"(CASE WHEN REQ2.ALLOC IS NULL THEN 0\r\n" + 
				"ELSE REQ2.ALLOC\r\n" + 
				"END) AS QTESTK  \r\n" + 
				"FROM\r\n" + 
				"(SELECT ITV.STOFCY_0 AS SITE,  YITM_ANRAV_0 AS CODERAV, ITM.ITMREF_0 AS CODEX3,SUM(ITV.PHYSTO_0 - ITV.GLOALL_0) AS STKDISPO \r\n" + 
				"FROM [x3v12prod].[PROD].ITMMVT ITV\r\n" + 
				"JOIN [x3v12prod].[PROD].ITMMASTER ITM ON ITM.ITMREF_0 = ITV.ITMREF_0\r\n" + 
				"WHERE ITV.STOFCY_0 in ('0076','0041')\r\n" + 
				"AND ITM.YITM_ANRAV_0 <> '' AND ITV.PHYSTO_0 > 0\r\n" + 
				"GROUP BY ITV.STOFCY_0 , YITM_ANRAV_0,ITM.ITMREF_0) REQ1\r\n" + 
				"LEFT JOIN\r\n" + 
				"(SELECT ITM.YITM_ANRAV_0 AS CODERAV, SUM(ALLQTY_0) AS ALLOC\r\n" + 
				"FROM [x3v12prod].[PROD].ITMMVT ITV\r\n" + 
				"JOIN [x3v12prod].[PROD].ITMMASTER ITM ON ITV.ITMREF_0 = ITM.ITMREF_0 AND ITM.YITM_ANRAV_0 <> '' AND ITM.TCLCOD_0 = 'SOFA'\r\n" + 
				"JOIN [x3v12prod].[PROD].SORDERQ SOQ ON ITM.ITMREF_0 = SOQ.ITMREF_0 AND SOQ.SALFCY_0 in ('0076','0041')\r\n" + 
				"AND SOQ.BPCORD_0 in ('0321','0322','0323','0086') \r\n" + 
				"WHERE ITV.STOFCY_0 in ('0076','0041') AND ITV.GLOALL_0 > 0\r\n" + 
				"GROUP BY ITM.YITM_ANRAV_0, ITM.ITMREF_0) REQ2 ON REQ1.CODERAV = REQ2.CODERAV) AS REQ3\r\n" + 
				"WHERE QTESTK > 0\r\n" + 
				"";
		PreparedStatement pstm = conn.prepareStatement(tools.modifyDatabaseAndSchema(getParams(),SQL));
		ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            TstkX3 stock = new TstkX3(
            		(short)1, /*i1ent */
            		rs.getString(2),/* chcodi */
            		Short.parseShort(rs.getString(1)),
            		rs.getString(3),/* codex3 */
            		rs.getDouble(4), /* f8qtestk */
            		0.0d, /* f8qteres */
            		0.0d, /* f8qtenl*/
            		0.0d, /* f8stkalert */
            		(long)0, /* didatrupt */
            		(long)0, /* didatalert */
            		(long)tools.DateToLong(), /* didatdent */
            		(long)0, /* didatdsor */
            		(long) 0, /* didatdinv */
            		new String(tools.getCurrentHour(0)));
            stocks.add(stock);
        }
		return stocks;
	}

	public List<Tstk> queryStockEcomrun (Connection conn) throws SQLException {
		ArrayList<Tstk> stocks = new ArrayList<Tstk>();
		

		String SQL = "SELECT * \r\n" + 
				"FROM \r\n" + 
				"(SELECT REQ1.CODERAV, REQ1.STKDISPO + \r\n" + 
				"(CASE WHEN REQ2.ALLOC IS NULL THEN 0\r\n" + 
				"ELSE REQ2.ALLOC\r\n" + 
				"END) AS QTESTK  \r\n" + 
				"FROM\r\n" + 
				"(SELECT YITM_ANRAV_0 AS CODERAV, SUM(ITV.PHYSTO_0 - ITV.GLOALL_0) AS STKDISPO\r\n" + 
				"FROM [x3v12prod].[PROD].ITMMVT ITV\r\n" + 
				"JOIN [x3v12prod].[PROD].ITMMASTER ITM ON ITM.ITMREF_0 = ITV.ITMREF_0\r\n" + 
				"WHERE ITV.STOFCY_0 in ('0076','0041')\r\n" + 
				"AND ITM.YITM_ANRAV_0 <> '' AND ITV.PHYSTO_0 > 0 \r\n" + 
				"GROUP BY ITM.TCLCOD_0, YITM_ANRAV_0) REQ1\r\n" + 
				"LEFT JOIN\r\n" + 
				"(SELECT ITM.YITM_ANRAV_0 AS CODERAV, SUM(ALLQTY_0) AS ALLOC\r\n" + 
				"FROM [x3v12prod].[PROD].ITMMVT ITV\r\n" + 
				"JOIN [x3v12prod].[PROD].ITMMASTER ITM ON ITV.ITMREF_0 = ITM.ITMREF_0 AND ITM.YITM_ANRAV_0 <> '' AND ITM.TCLCOD_0 = 'SOFA'\r\n" + 
				"JOIN [x3v12prod].[PROD].SORDERQ SOQ ON ITM.ITMREF_0 = SOQ.ITMREF_0 AND SOQ.SALFCY_0 in ('0076','0041')\r\n" + 
				"AND SOQ.BPCORD_0 in ('0321','0322','0323','0086', '0009') \r\n" + 
				"WHERE ITV.STOFCY_0 in ('0076','0041') AND ITV.GLOALL_0 > 0 \r\n" + 
				"GROUP BY ITM.YITM_ANRAV_0, ITM.ITMREF_0) REQ2 ON REQ1.CODERAV = REQ2.CODERAV) AS REQ3\r\n" + 
				"WHERE QTESTK > 0";
		PreparedStatement pstm = conn.prepareStatement(tools.modifyDatabaseAndSchema(getParams(),SQL));
		ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Tstk stock = new Tstk(
            		(short)1, /*i1ent */
            		rs.getString(1),/* chcodi */
            		(short)89,/* i1depot */ 
            		rs.getDouble(2), /* f8qtestk */
            		0.0d, /* f8qteres */
            		0.0d, /* f8qtenl*/
            		0.0d, /* f8stkalert */
            		(long)0, /* didatrupt */
            		(long)0, /* didatalert */
            		(long)tools.DateToLong(), /* didatdent */
            		(long)0, /* didatdsor */
            		(long) 0, /* didatdinv */
            		new String(tools.getCurrentHour(0)));
            stocks.add(stock);
        }
		return stocks;
	}
	/*
		Ingres
	*/ 
	public ResponseStock queryStockIngres (Connection conn,QueryStock query) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		ResponseStock responseStock = new ResponseStock();

			String SQL = "select i1depot as supplier, chcodi as coderav,f8qtestk-f8qteres as quantity \r\n"
			+ " from tstk \r\n"
			+ " where i1ent=1 \r\n"
			+ " and i1depot= ? \r\n"
			+ " and chcodi in (? )  \r\n";
	
				PreparedStatement pstm = conn.prepareStatement(SQL);
				for (Stock item : query.getStock())   
				{  
					pstm.setInt(1, item.getI1depot());
					pstm.setString(2, item.getChcodi());
					ResultSet rs = pstm.executeQuery();
					while (rs.next()) {
						Stock stock = new Stock(
								rs.getShort(1), /*i1depot */
								rs.getString(2),/* chcodi */
								rs.getDouble(3) /* f8qtestk */
						);
						stocks.add(stock);
					}
				}  
		
				responseStock.setStock(stocks);
	
		return responseStock;
	}

	public ResponseTarif queryTarifFromIngres (Connection conn,QueryTarif query) throws SQLException {
		ArrayList<Tarif> tarifs = new ArrayList<Tarif>();
		ResponseTarif responseTarif = new ResponseTarif();

			String SQL = "select a.chcodi as coderav, a.mfpvttc as public ,a.mfpromo as promo,\r\n"
			+ " a.didatdeb as debut,a.didatfin as fin ,c.f4txtva  \r\n"
			+ " from ttar a , tart b, rtva c  \r\n"
			+ " where a.i1ent=1 and b.i1ent=1 \r\n"
			+ " and a.chcodi = b.chcodi  and b.chctva = c.chctva \r\n"
			+ " and a.chcodi = (?) \r\n"
			+ " and a.i1notar=1 and a.i1type=0 \r\n"; 
	
				PreparedStatement pstm = conn.prepareStatement(SQL);
				for (ArticleTarif item : query.getTarif())   
				{  
					pstm.setString(1, item.getChcodi());
					ResultSet rs = pstm.executeQuery();
					while (rs.next()) {
						Tarif tarif = new Tarif(
								rs.getString(1), /*chcodi */
								rs.getDouble(2), /* mfpvttc */
								rs.getDouble(3), /* mfpromo */
								rs.getInt(4), /* Date de debut */
								rs.getInt(5), /* Date de fin */
								rs.getDouble(6) /*  taux de tva */
						);
						tarifs.add(tarif);
					}
				}  
		
				responseTarif.setTarif(tarifs);
	
		return responseTarif;
	}

	public ResponseStock queryStockIngresByGencod (Connection conn,QueryStock query) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		ResponseStock responseStock = new ResponseStock();

			String SQL = "select i1depot as supplier, chgencod as coderav,f8qtestk-f8qteres as quantity \r\n"
			+ " from tstk a, tart b\r\n"
			+ " where a.i1ent=b.i1ent and a.i1ent=1 \r\n"
			+ " and a.i1depot= ? \r\n"
			+ " and a.chcodi = b.chcodi and b.chgencod IN ( ? )  and a.f8qtestk  > 0 \r\n";
	
				PreparedStatement pstm = conn.prepareStatement(SQL);
				for (Stock item : query.getStock())   
				{  
					pstm.setInt(1, item.getI1depot());
					pstm.setString(2, item.getChcodi());
					ResultSet rs = pstm.executeQuery();
					while (rs.next()) {
						Stock stock = new Stock(
								rs.getShort(1), /*i1depot */
								rs.getString(2),/* chcodi */
								rs.getDouble(3) /* f8qtestk */
						);
						stocks.add(stock);
					}
				}  
		
				responseStock.setStock(stocks);
	
		return responseStock;
	}
	
	public void insertTstk(Connection conn,List<Tstk> stocks ) {
			// post data to local database 
			String SQL = "INSERT INTO tstk VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			 try {
		        PreparedStatement session = conn.prepareStatement(SQL); 
		        for(Tstk stock: stocks) {
		        	session.setShort(1, stock.getI1ent());
		        	session.setString(2, stock.getChcodi());
		        	session.setShort(3, stock.getI1depot());
		        	session.setDouble(4, stock.getF8qtestk());
		        	session.setDouble(5, stock.getF8qteres());
		        	session.setDouble(6, stock.getF8qtenl());
		        	session.setDouble(7,stock.getF8stkalert());
		        	session.setLong(8, stock.getDidatrupt());
		        	session.setLong(9, stock.getDidatalert());
		        	session.setLong(10, stock.getDidatdent());
		        	session.setLong(11, stock.getDidatdsor());
		        	session.setLong(12, stock.getDidatdinv());
		        	session.setString(13, stock.getVcempl());
			        session.executeUpdate();
		        }
//	    		conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
		    	   e.printStackTrace();
		     }
		}

	private void insertApproIntoDatabase(Connection conn, TransactionForAppro transaction) {
			/* Header  */
			String SQLHEADER = "INSERT INTO Tappent VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
			   PreparedStatement session = conn.prepareStatement(SQLHEADER); 
				   session.setInt(1, transaction.getHeader().getI1depot());
				   session.setInt(2, transaction.getHeader().getI1natmvt());
				   session.setInt(3, transaction.getHeader().getI4numbl());
				   session.setInt(4,transaction.getHeader().getI1depote());
				   session.setInt(5,transaction.getHeader().getI2derlig());
				   session.setInt(6,transaction.getHeader().getI1nbmaj());
				   session.setInt(7,transaction.getHeader().getI1nbedit());
				   session.setString(8,transaction.getHeader().getVcobs());
				   session.setString(9,transaction.getHeader().getVcusrbl());
				   session.setString(10,transaction.getHeader().getChsolde());
				   session.setLong(11, transaction.getHeader().getDidatcre());
				   session.setString(12, transaction.getHeader().getVcusrcre());
				   session.setLong(13, transaction.getHeader().getDidatann());
				   session.setString(14, transaction.getHeader().getChheurann());
				   session.setString(15, transaction.getHeader().getVcusrann());
				   session.executeUpdate();
   
		   } catch (SQLException e) {
			   // TODO Auto-generated catch block
				  e.printStackTrace();
			}

			/* Boday  */
			String SQLLIG = "INSERT INTO Tapplig VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
			   PreparedStatement session = conn.prepareStatement(SQLLIG); 
			   for(Tapplig body: transaction.getBody()) {
				   session.setInt(1, body.getI1depot());
				   session.setInt(2, body.getI1natmvt());
				   session.setInt(3, body.getI4numbl());
				   session.setInt(4, body.getI2numlig());
				   session.setString(5, body.getChcodi());
				   session.setDouble(6, body.getF8qte());
				   session.setString(7, body.getChunite());
				   session.setDouble(8, body.getF8qteustk());
				   session.setString(9, body.getChunitstk());
				   session.setDouble(10, body.getF8qteustkdd());
				   session.setString(11,body.getChsolde());
				   session.setLong(12, body.getDidatcre());
				   session.setString(13, body.getChheurcre());
				   session.setString(14, body.getVcusrcre());
				   session.setLong(15, body.getDidatmaj());
				   session.setString(16, body.getVcusrmaj());
				   session.executeUpdate();
			   }
		   } catch (SQLException e) {
			   // TODO Auto-generated catch block
				  e.printStackTrace();
			}
	}
	
	public void insertIntoTappent(Connection conn,Tappent header ) {
		// post data to local database 
		String SQL = "INSERT INTO Tappent VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 try {
			PreparedStatement session = conn.prepareStatement(SQL); 
				session.setInt(1, header.getI1depot());
				session.setInt(2, header.getI1natmvt());
				session.setInt(3, header.getI4numbl());
				session.setInt(4,header.getI1depote());
				session.setInt(5,header.getI2derlig());
				session.setInt(6,header.getI1nbmaj());
				session.setInt(7,header.getI1nbedit());
				session.setString(8,header.getVcobs());
				session.setString(9,header.getVcusrbl());
				session.setString(10,header.getChsolde());
				session.setLong(11, header.getDidatcre());
				session.setString(12, header.getVcusrcre());
				session.setLong(13, header.getDidatann());
				session.setString(14, header.getChheurann());
				session.setString(15, header.getVcusrann());
				session.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		 }
	}
	
	public void insertIntoTapplig(Connection conn,List<Tapplig> bodies ) {

		String SQL = "INSERT INTO Tapplig VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 try {
			PreparedStatement session = conn.prepareStatement(SQL); 
			for(Tapplig body: bodies) {
				session.setInt(1, body.getI1depot());
				session.setInt(2, body.getI1natmvt());
				session.setInt(3, body.getI4numbl());
				session.setInt(4, body.getI2numlig());
				session.setString(5, body.getChcodi());
				session.setDouble(6, body.getF8qte());
				session.setString(7, body.getChunite());
				session.setDouble(8, body.getF8qteustk());
				session.setString(9, body.getChunitstk());
				session.setDouble(10, body.getF8qteustkdd());
				session.setString(11,body.getChsolde());
				session.setLong(12, body.getDidatcre());
				session.setString(13, body.getChheurcre());
				session.setString(14, body.getVcusrcre());
				session.setLong(15, body.getDidatmaj());
				session.setString(16, body.getVcusrmaj());
				session.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		 }
	}

	public Rdepot  queryDepotFromIngres(Connection conn,short i1depot ) throws SQLException {
		String SQL = "select i1depot,i1groupdep from rdepot where i1ent=1 and i1depot   = ? ";
		try {
			Rdepot depot = new Rdepot();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setShort(1, i1depot);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				depot = new Rdepot (
					rs.getShort(1), /* i1depot */
					rs.getShort(2) /*i1groupdep */
				);
			}
			return depot;
	   	} catch (SQLException e) {
				   System.out.print("Error occurred while SELECT Article: " + e);
				   throw e;
		}					
	}

	public Article queryArticleFromIngres(Connection conn,String reference ) throws SQLException {
		String SQL = "select chcodi,chgencod,chunitstk from tart where i1ent=1 and chcodi  = ? ";
		try {
			Article article = new Article();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setString(1, reference);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				article = new Article (
					rs.getString(1), /*chcodi */
					rs.getString(2), /*chgencod */
					rs.getString(3) /*chunitstk */
				);
			}
			return article;
	   	} catch (SQLException e) {
				   System.out.print("Error occurred while SELECT Article: " + e);
				   throw e;
		}					
	}

	public Tnumbl queryCompteurFromIngres(Connection conn,short i1depot, short i1natmvt ) throws SQLException {
		String SQL = "select i1ent,i1depot,i1natmvt,i4numbl,cfil1024 from tnumbl where i1ent=1 and i1depot = ? and i1natmvt = ?";
		try {
			Tnumbl compteur = new Tnumbl();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, i1depot);
			pstm.setInt(2, i1natmvt);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Tnumbl numbl = new Tnumbl(
					rs.getShort(1), /*i1ent */
					rs.getShort(2), /*i1depot */
					rs.getShort(3), /*i1natmvt */
					rs.getInt(4), /*i4numbl */
					rs.getString(5)/* cfil1024 */
				);
				compteur = numbl;
			}
			return compteur;
	   	} catch (SQLException e) {
				   System.out.print("Error occurred while SELECT Tnumbl: " + e);
				   throw e;
		}					
	}
	
	public void updateCompteurFromIngres (Connection conn, short i1depot, short i1natmvt) throws SQLException, ClassNotFoundException {
		String updateCompteur = "update tnumbl set i4numbl = i4numbl + 1 where i1ent=1 and i1depot = ? and i1natmvt = ? ";
		try {
			PreparedStatement pstm = conn.prepareStatement(updateCompteur);
			pstm.setInt(1, i1depot);
			pstm.setInt(2, i1natmvt);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.print("Error occurred while UPDATE  Operation: " + e);
			throw e;
		}
	}

	
	public ResponseTransfert /*TransactionForAppro*/  requestForAppro(Connection conn, QueryTransfert produits ) throws ClassNotFoundException, SQLException {

	Target target = new Target();
	ResponseTransfert resp = new ResponseTransfert();
	TransactionForAppro transaction = new TransactionForAppro();
	ArrayList<Appro> listappro = new ArrayList<Appro>();
	short defaultDepot = produits.getTransfert().get(0).getI1depot();
	short i1groupdep = queryDepotFromIngres(conn, defaultDepot).getI1groupdep();
	if (i1groupdep == 8) {
		target.setSens((short)1);
		target.setEmitter(defaultDepot);
		target.setReceiver((short)51);
		target.setProducts(produits);
		transaction = createAppro(conn,target);
		Appro appro = new Appro();
		appro.setMessage(String.format("Numero appro : %d Depot - emetteur %d  - Depot livraison %d - Nature mouvement :%d ",
										transaction.getHeader().getI4numbl(),
										transaction.getHeader().getI1depote(),
										transaction.getHeader().getI1depot(),
										transaction.getHeader().getI1natmvt()));

		appro.setAppro(transaction.getHeader().getI4numbl());
		appro.setType(transaction.getHeader().getI1natmvt());
		listappro.add(appro);
		insertApproIntoDatabase(conn, transaction);

	} else {
		target.setEmitter((short)14);
		target.setReceiver(defaultDepot);
		target.setProducts(produits);
		target.setSens((short) -1);
		transaction = createAppro(conn,target);
		Appro approNegatif = new Appro();
		approNegatif.setMessage(String.format("Numero appro : %d Depot - emetteur %d  - Depot livraison %d - Nature mouvement :%d ",
										transaction.getHeader().getI4numbl(),
										transaction.getHeader().getI1depote(),
										transaction.getHeader().getI1depot(),
										transaction.getHeader().getI1natmvt()));
		approNegatif.setAppro(transaction.getHeader().getI4numbl());
		approNegatif.setType(transaction.getHeader().getI1natmvt());
		listappro.add(approNegatif);
		insertApproIntoDatabase(conn,transaction);
		/* 14 -> 51 */
		target.setEmitter((short)14);
		target.setReceiver((short) 51);
		target.setSens((short)1);
		transaction = createAppro(conn,target);
		Appro approPositif = new Appro();
		approPositif.setMessage(String.format("Numero appro : %d Depot - emetteur %d  - Depot livraison %d - Nature mouvement :%d ",
										transaction.getHeader().getI4numbl(),
										transaction.getHeader().getI1depote(),
										transaction.getHeader().getI1depot(),
										transaction.getHeader().getI1natmvt()));

		approPositif.setAppro(transaction.getHeader().getI4numbl());
		approPositif.setType(transaction.getHeader().getI1natmvt());
		listappro.add(approPositif);
		insertApproIntoDatabase(conn,transaction);
	}

		
	resp.setAppro(listappro);
	
	return resp ; /*  transaction; */

}



private TransactionForAppro createAppro(Connection conn,Target target) throws SQLException, ClassNotFoundException {
	TransactionForAppro transaction = new TransactionForAppro();
	Tapplig line = new Tapplig();
	ArrayList<Tapplig> listeapp = new ArrayList<Tapplig>();
	short numlig = 1;
	String refcli = target.getProducts().getTransfert().get(0).getVcrefcli();
	Tnumbl numero = new Tnumbl();
	Tappent header = new Tappent();
	numero = queryCompteurFromIngres(conn, target.getEmitter(),(short) 1);

	for (Transfert appro: target.getProducts().getTransfert()) {
		Article article = queryArticleFromIngres(conn, appro.getChcodi());
		line = new Tapplig(target.getEmitter(), (short)1, numero.getI4numbl(), numlig, article.getChcodi(), 
				appro.getF8qte() * target.getSens(), article.getChunitstk(), appro.getF8qte() * target.getSens(), article.getChunitstk(), 0, 
				new String("N"), tools.DateToLong(), tools.getCurrentHour(), new String("INGNET"), 0, new String());
		listeapp.add(line);			
		numlig++;
	}

	try {
		header.setI1depot(target.getEmitter());
		header.setI1depote(target.getReceiver());
		header.setI4numbl(numero.getI4numbl());
		header.setChsolde(new String("N"));
		header.setI1natmvt((short)1);
		header.setI2derlig(--numlig);
		header.setI1nbedit((short)0);
		header.setVcobs(refcli);
		header.setDidatcre(tools.DateToLong());
		header.setI1nbmaj((short)0);
		header.setDidatann((long)0);
		header.setVcusrann(new String());
		header.setVcusrbl(new String());
		header.setVcusrcre("INGNET".toString());
		header.setChheurann(new String());
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	transaction.setBody(listeapp);
	transaction.setHeader(header);
	updateCompteurFromIngres(conn, target.getEmitter(), (short)1);
	return transaction;
	}

public void deleteTstk(Connection ingres) throws SQLException {
	// TODO Auto-generated method stub
	String SQL = "DELETE FROM tstk WHERE i1ent=1 AND i1depot=89";
	 try {
           PreparedStatement pstm = ingres.prepareStatement(SQL);
           pstm.executeUpdate();
//	  		conn.commit();
	    } catch (SQLException e) {
	        System.out.print("Error occurred while DELETE: " + e);
	        throw e;
	    }					
	}
}

