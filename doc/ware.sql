--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:24:00

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1504 (class 1259 OID 17489)
-- Dependencies: 1776 1777 1778 1779 1780 1781 6
-- Name: ware; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE ware (
    id integer NOT NULL,
    name character varying(128) NOT NULL,
    cost real DEFAULT 0 NOT NULL,
    price real DEFAULT 0 NOT NULL,
    number integer NOT NULL,
    barcode character varying(16) NOT NULL,
    status integer DEFAULT 0 NOT NULL,
    idx_name tsvector,
    category_id integer DEFAULT 0 NOT NULL,
    lowest_cost real NOT NULL,
    number_alarm integer DEFAULT (-1) NOT NULL,
    number_alarm_enable integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.ware OWNER TO erp;

--
-- TOC entry 1507 (class 1259 OID 17506)
-- Dependencies: 1504 6
-- Name: ware_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE ware_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ware_id_seq OWNER TO erp;

--
-- TOC entry 1791 (class 0 OID 0)
-- Dependencies: 1507
-- Name: ware_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE ware_id_seq OWNED BY ware.id;


--
-- TOC entry 1782 (class 2604 OID 17513)
-- Dependencies: 1507 1504
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE ware ALTER COLUMN id SET DEFAULT nextval('ware_id_seq'::regclass);


--
-- TOC entry 1787 (class 2606 OID 17532)
-- Dependencies: 1504 1504
-- Name: ware_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware
    ADD CONSTRAINT ware_p_key PRIMARY KEY (id);


--
-- TOC entry 1783 (class 1259 OID 17539)
-- Dependencies: 1504
-- Name: ware_barcode_index; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX ware_barcode_index ON ware USING btree (barcode);


--
-- TOC entry 1784 (class 1259 OID 17540)
-- Dependencies: 1504
-- Name: ware_category_index; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX ware_category_index ON ware USING btree (category_id);


--
-- TOC entry 1785 (class 1259 OID 17541)
-- Dependencies: 1504
-- Name: ware_idxname_fti; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX ware_idxname_fti ON ware USING gist (idx_name);


--
-- TOC entry 1788 (class 1259 OID 17542)
-- Dependencies: 1504
-- Name: ware_status_index; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX ware_status_index ON ware USING btree (status);


-- Completed on 2009-05-04 20:24:00

--
-- PostgreSQL database dump complete
--

